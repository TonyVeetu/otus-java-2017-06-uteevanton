package uteevbkru.help;


import java.util.List;

public class TableColumnsFields {
    private String tableName;
    private ColumnNameField primaryKey;
    private List<ColumnNameField> columns;

    public TableColumnsFields(String name, ColumnNameField primaryKey, List<ColumnNameField> columns) {
        this.tableName = name;
        this.primaryKey = primaryKey;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ColumnNameField getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ColumnNameField primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<ColumnNameField> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnNameField> columns) {
        this.columns = columns;
    }
}
