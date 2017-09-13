package uteevbkru.dataset;


import uteevbkru.executor.TExecutor;
import uteevbkru.main.TableHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by anton on 10.09.17.
 */
public class UsersDAO {
    private TExecutor exec;

    public UsersDAO(Connection connection){
        exec = new TExecutor(connection);
    }

    public <T extends DataSet>T load(Object primaryKey, final Class<T> clazz) throws SQLException {
        T result = null;
        TableHelper tabHelp = new TableHelper(clazz);
        if(tabHelp != null) {
            String query = "SELECT * FROM " + tabHelp.getTableName(clazz) + " WHERE id=" + primaryKey;//TODO Need i get with reflection id?

            result = exec.execQuery(query, resultSet -> {
                T instance = null;

                Method m = null;
                Class<? super T> superClazz = clazz.getSuperclass();
                Class[] paramTypes = new Class[] { long.class};
                try {
                    m = superClazz.getDeclaredMethod("setId", paramTypes);
                    m.setAccessible(true);
                }
                catch (NoSuchMethodException e){
                    e.printStackTrace();
                }

                int columns = resultSet.getMetaData().getColumnCount();
                int id = 0; String name = null; int age = 0;
                if(resultSet.next()) {
                    for (int i = 1; i <= columns; i++){
                        if(i == 1)
                            id = resultSet.getInt(i);
                        if(i == 2)
                            name = resultSet.getString(i);
                        if(i == 3)
                            age = resultSet.getInt(i);
                    }
                    System.out.println();
                    try {
                        instance = clazz.getConstructor(String.class, int.class).newInstance(name, age);//выставляю полученные из базы поля!

                        if(m != null){
                            Object[] args = new Object[] {new Long (id) };
                            m.invoke(instance, args);//выставляю полученое из базы поле ID!
                        }
                    }
                    catch (NoSuchMethodException e ){
                        e.printStackTrace();
                    }
                    catch (InvocationTargetException e ){
                        e.printStackTrace();
                    }
                }
                return instance;
            });
        }
        return result;
    }

    public <T extends DataSet> void save(T user) throws SQLException {
        System.out.println(TableHelper.makeSaveQuery(user, user.getClass()));
        String query = TableHelper.makeSaveQuery(user, user.getClass());
        exec.execUpdate(query);
    }


}

