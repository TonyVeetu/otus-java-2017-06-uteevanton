package uteevbkru.help;

import uteevbkru.model.DataSet;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class TableHelper {

    private TableHelper(){
    }

    public static TableColumnsFields getTableColumnsFields(Class<? extends DataSet> clazz) {
        TableColumnsFields tableData = null;
        ColumnNameField primaryKey = null;
        List<ColumnNameField> columns = new ArrayList<>();

        String tableName = getTableName(clazz);
        List<Field> fields = getEntityFields(clazz);

        for(Field field : fields){
            ColumnNameField column = getColumnNameField(field);
            if(primaryKey == null && field.getAnnotation(Id.class) != null){
                if(column == null)
                    primaryKey = new ColumnNameField(field.getName(), field);
                else
                    primaryKey = column;
            }
            else if (column != null)
                columns.add(column);
        }
        if(primaryKey != null)
            tableData = new TableColumnsFields(tableName, primaryKey, columns);
        return tableData;
    }

    private static String getTableName(Class<? extends DataSet> clazz) {
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        Entity entityAnnotation = clazz.getAnnotation(Entity.class);
        String tableName;

        if(tableAnnotation != null && !tableAnnotation.name().equals(""))
            tableName = tableAnnotation.name();
        else
            tableName = entityAnnotation.name();
        return tableName;
    }

    private static List<Field> getEntityFields(Class<? extends DataSet> clazz) {
        TreeSet<Field> fieldSet = new TreeSet<>((f1, f2) -> {
            if(f1.getName().equals(f2.getName()))
                return 0;
            else
                return 1;
        });

        fieldSet.addAll(Arrays.asList(clazz.getDeclaredFields()));
        Class<?> superclass = clazz.getSuperclass();
        while(!superclass.equals(Object.class)){
            MappedSuperclass superclassAno = superclass.getAnnotation(MappedSuperclass.class);
            if (superclassAno != null)
                fieldSet.addAll(Arrays.asList(superclass.getDeclaredFields()));
            superclass = superclass.getSuperclass();
        }
        return new ArrayList<>(fieldSet);
    }

    private static ColumnNameField getColumnNameField(Field field) {
        field.setAccessible(true);
        Column columnAnnotation = field.getAnnotation(Column.class);
        ColumnNameField column = null;

        if(columnAnnotation != null){
            if(!columnAnnotation.name().equals(""))
                column = new ColumnNameField(columnAnnotation.name(), field);
            else
                column = new ColumnNameField(field.getName(), field);
        }
        return column;
    }
}