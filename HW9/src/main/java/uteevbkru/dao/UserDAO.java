package uteevbkru.dao;

import uteevbkru.help.ColumnNameField;
import uteevbkru.help.QueryHelper;
import uteevbkru.help.TableColumnsFields;
import uteevbkru.model.DataSet;
import uteevbkru.executor.TExecutor;
import uteevbkru.help.TableHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private TExecutor exec;

    public UserDAO(Connection connection){
        exec = new TExecutor(connection);
    }

//    public <T extends DataSet>T load(Object primaryKey, final Class<T> clazz) throws SQLException {
//        T result = null;
//        TableHelper tableHelper = new TableHelper(clazz);
//        if(tableHelper != null) {
//            String query = "SELECT * FROM " + tableHelper.getTableName(clazz) + " WHERE id=" + primaryKey;//TODO Need i get with reflection id?
//
//            result = exec.execQuery(query, resultSet -> {
//                T instance = null;
//
//                Method method = null;
//                Class<? super T> superClazz = clazz.getSuperclass();
//                Class[] paramTypes = new Class[] { long.class};
//                try {
//                    method = superClazz.getDeclaredMethod("setId", paramTypes);
//                    method.setAccessible(true);
//                }
//                catch (NoSuchMethodException e){
//                    e.printStackTrace();
//                }
//
//                int countOfColumns = resultSet.getMetaData().getColumnCount();
//                int id = 0; String name = null; int age = 0;
//                if(resultSet.next()) {
//                    for (int i = 1; i <= countOfColumns; i++){
//                        if(i == 1)
//                            id = resultSet.getInt(i);
//                        if(i == 2)
//                            name = resultSet.getString(i);
//                        if(i == 3)
//                            age = resultSet.getInt(i);
//                    }
//                    System.out.println();
//                    try {
//                        instance = clazz.getConstructor(String.class, int.class).newInstance(name, age);//выставляю полученные из базы поля!
//
//                        if(method != null){
//                            Object[] args = new Object[] {new Long (id) };
//                            method.invoke(instance, args);//выставляю полученое из базы поле ID!
//                        }
//                    }
//                    catch (NoSuchMethodException e ){
//                        e.printStackTrace();
//                    }
//                    catch (InvocationTargetException e ){
//                        e.printStackTrace();
//                    }
//                }
//                return instance;
//            });
//        }
//        return result;
//    }


    public <T extends DataSet> T load(Object primaryKey, final Class<T> clazz) {
        T result = null;
        final TableColumnsFields tableInfo = TableHelper.makeTableInfo(clazz);
        if (tableInfo != null) {
            String query = QueryHelper.makeLoadQuery(primaryKey, tableInfo);
            if (query != null) {
                result = exec.execQuery(query, resultSet -> {
                    T instance = null;
                    if (resultSet.next()) {
                        instance = clazz.newInstance();

                        ColumnNameField pkColumn = tableInfo.getPrimaryKey();
                        Field pkColumnField = pkColumn.getField();
                        Class<?> pkColumnClass = pkColumnField.getType();
                        pkColumnField.set(instance, resultSet.getObject(pkColumn.getName(), pkColumnClass));

                        for (ColumnNameField column : tableInfo.getColumns()) {
                            Field columnField = column.getField();
                            Class<?> columnClass = columnField.getType();
                            columnField.set(instance, resultSet.getObject(column.getName(), columnClass));
                        }
                    }
                    return instance;
                });
            }
        }
        return result;
    }

    public <T extends DataSet> void save(T user) throws SQLException {
        System.out.println(TableHelper.makeSaveQuery(user, user.getClass()));
        String query = TableHelper.makeSaveQuery(user, user.getClass());
        exec.execUpdate(query);
    }
}

