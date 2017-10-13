package uteevbkru.help;

import uteevbkru.model.DataSet;

import java.util.List;
import java.util.stream.Collectors;

public class QueryHelper {

    private QueryHelper() {
    }

    public static <T extends DataSet> String makeSaveQuery(final T object, TableColumnsFields tableInfo) {
        String query = "INSERT INTO \"" + tableInfo.getName() + "\" ";

        List<ColumnNameField> columns = tableInfo.getColumns().stream().filter(c -> {
            Object obj;
            try {
                obj = c.getField().get(object);
            } catch (IllegalAccessException e) {
                obj = null;
            }
            return obj != null;
        }).collect(Collectors.toList());

        //TODO think!!
        //TODO
        String columnsName = columns.stream()
                .map(ColumnNameField::getName)
                .collect(Collectors.joining(",", "(", ")"));

        String columnsValue = columns.stream()
                .map(c -> {
                    try {
                        Object obj = c.getField().get(object);
                        return getValueStringForSQL(obj);
                    } catch (IllegalAccessException e) {
                        // Is never happens
                        return null;
                    }
                }).collect(Collectors.joining(",", "(", ")"));

        query += columnsName + " VALUES " + columnsValue;
        return query;
    }

    public static String makeLoadQuery(final Object primaryKey, TableColumnsFields table) {
        if (primaryKey != null) {
            return "SELECT * FROM \""
                    + table.getName()
                    + "\" WHERE "
                    + table.getPrimaryKey().getName() + " = " + getValueStringForSQL(primaryKey);
        } else {
            return null;
        }
    }

    public static String getValueStringForSQL(Object value) {
        if (value instanceof String) {
            return "'" + value.toString() + "'";
        } else {
            return value.toString();
        }
    }
}
