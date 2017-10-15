package uteevbkru.help;

import uteevbkru.model.DataSet;

import java.util.List;
import java.util.stream.Collectors;

public class QueryHelper {

    private QueryHelper() {
    }

    public static <T extends DataSet> String makeSaveQuery(final T object, TableColumnsFields tableData) {
        String saveQuery = "INSERT INTO \"" + tableData.getTableName() + "\" ";

        List<ColumnNameField> columns = tableData.getColumns().stream().filter(column -> {
            Object obj;
            try {
                obj = column.getField().get(object);
            }
            catch (IllegalAccessException e) {
                obj = null;
            }
            return obj != null;
        }).collect(Collectors.toList());

        String columnsName = columns.stream()
                .map(ColumnNameField::getName)
                .collect(Collectors.joining(",", "(", ")"));

        String columnsValue = columns.stream()
                .map(c -> {
                    try {
                        Object obj = c.getField().get(object);
                        return getValueStringForSQL(obj);
                    }
                    catch (IllegalAccessException e) {
                        return null;
                    }
                }).collect(Collectors.joining(",", "(", ")"));

        saveQuery += columnsName + " VALUES " + columnsValue;
        System.out.println("saveQuery = " + saveQuery);
        return saveQuery;
    }

    public static String makeLoadQuery(final Object primaryKey, TableColumnsFields tableData) {
        if(primaryKey != null){
            System.out.println("loadQuery = " + "SELECT * FROM \""
                    + tableData.getTableName()
                    + "\" WHERE "
                    + tableData.getPrimaryKey().getName() + " = " + getValueStringForSQL(primaryKey));
            return "SELECT * FROM \""
                    + tableData.getTableName()
                    + "\" WHERE "
                    + tableData.getPrimaryKey().getName() + " = " + getValueStringForSQL(primaryKey);
        }
        else
            return null;
    }

    public static String getValueStringForSQL(Object value) {
        if(value instanceof String){
            return "'" + value.toString() + "'";
        }
        else
            return value.toString();
    }
}
