package uteevbkru.dao;

import uteevbkru.help.ColumnNameField;
import uteevbkru.help.QueryHelper;
import uteevbkru.help.TableColumnsFields;
import uteevbkru.model.DataSet;
import uteevbkru.executor.TExecutor;
import uteevbkru.help.TableHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private TExecutor exec;

    public UserDAO(Connection connection){
        exec = new TExecutor(connection);
    }

    public <T extends DataSet> T load(Object primaryKey, final Class<T> clazz) {
        T result = null;
        final TableColumnsFields tableData = TableHelper.getTableColumnsFields(clazz);
        if (tableData != null) {
            String query = QueryHelper.makeLoadQuery(primaryKey, tableData);
            if (query != null) {
                result = exec.execQuery(query, resultSet -> {
                    T instance = null;
                    if (resultSet.next()) {
                        instance = clazz.newInstance();
                        setKeyFieldInInstance(tableData, instance, resultSet);
                        setColumnsFieldsInInstance(tableData,instance, resultSet);
                    }
                    return instance;
                });
            }
        }
        return result;
    }

    private void setKeyFieldInInstance(TableColumnsFields tableData, Object instance, ResultSet resultSet){
        ColumnNameField keyColumn = tableData.getPrimaryKey();
        Field keyField = keyColumn.getField();
        try {
            keyField.set(instance, resultSet.getObject(keyColumn.getName()));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e ){
            e.printStackTrace();
        }
    }

    private void setColumnsFieldsInInstance(TableColumnsFields tableData, Object instance, ResultSet resultSet){
        for (ColumnNameField column : tableData.getColumns()) {
            Field columnField = column.getField();
            try {
                columnField.set(instance, resultSet.getObject(column.getName()));
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            catch (IllegalAccessException e ){
                e.printStackTrace();
            }
        }
    }

    public <T extends DataSet> void save(T user) throws SQLException {
        TableColumnsFields tableData = TableHelper.getTableColumnsFields(user.getClass());
        if (tableData != null) {
            String query = QueryHelper.makeSaveQuery(user, tableData);
            exec.execUpdate(query);
        }
    }
}

