package uteevbkru.help;


import java.util.List;

public class TableColumnsFields {
    private String name;
    private ColumnNameField primaryKey;
    private List<ColumnNameField> columns;

    public TableColumnsFields(String name, ColumnNameField primaryKey, List<ColumnNameField> columns) {
        this.name = name;
        this.primaryKey = primaryKey;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
