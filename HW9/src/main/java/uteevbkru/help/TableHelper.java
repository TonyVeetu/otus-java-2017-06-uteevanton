package uteevbkru.help;

import uteevbkru.model.DataSet;
import uteevbkru.model.UserDataSet;

import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class TableHelper {

    private TableHelper(){
    }

    public static TableColumnsFields makeTableInfo(Class<? extends DataSet> clazz) {
        TableColumnsFields tableInfo = null;
        ColumnNameField primaryKey = null;
        List<ColumnNameField> columns = new ArrayList<>();

        String tableName = getTableName(clazz);
        List<Field> fields = getEntityFields(clazz);

        for(Field field : fields){
            ColumnNameField column = getColumnInfo(field);

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
            tableInfo = new TableColumnsFields(tableName, primaryKey, columns);
        return tableInfo;
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

    //TODO think!!
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

    private static ColumnNameField getColumnInfo(Field field) {
        field.setAccessible(true);
        Column columnAnnotation = field.getAnnotation(Column.class);
        ColumnNameField column = null;

        if(columnAnnotation != null){
            if(!columnAnnotation.name().equals(""))
                column = new ColumnNameField(columnAnnotation.name(), field);
            else
                column = new ColumnNameField(field.getName(), field);//TODO
        }
        return column;
    }

//    public static String getTableName(Class clazz){
//        String name = null;
//        Annotation an = clazz.getDeclaredAnnotation(Table.class);
//        if(an != null) {
//            Table anTable = (Table) an;
//            name = anTable.name();
//        }
//        return name;
//    }
//
//    public static String getNameOfColumnName(){
//        String name = null;
//        Field[] fds = UserDataSet.class.getDeclaredFields();
//        for (Field f : fds) {
//            Column annColumn = f.getAnnotation(Column.class);
//                if(f.getName().equals("name")) {
//                    name = annColumn.name();
//                }
//        }
//        return name;
//    }
//
//    public static String getNameOfColumnAge(){
//        String name = null;
//        Field[] fds = UserDataSet.class.getDeclaredFields();
//        for (Field f : fds) {
//            Column annColumn = f.getAnnotation(Column.class);
//            if(f.getName().equals("age")) {
//                name = annColumn.name();
//            }
//        }
//        return name;
//    }
//
    public static String getValueOfColumnName(Object object){
        Object myObj = null;
        Field[] fds = UserDataSet.class.getDeclaredFields();
        for (Field f : fds) {
            if(f.getName().equals("name")) {
                f.setAccessible(true);
                try {
                    myObj = f.get(object);
                }
                catch(IllegalAccessException e ){
                    e.printStackTrace();
                }
            }
        }
        return myObj.toString();
    }

    public static String getValueOfColumnAge(Object object){
        Object myObj = null;
        Field[] fds = UserDataSet.class.getDeclaredFields();
        for (Field f : fds) {
            if(f.getName().equals("age")) {
                f.setAccessible(true);
                try {
                    myObj = f.get(object);
                }
                catch(IllegalAccessException e ){
                    e.printStackTrace();
                }
            }
        }
        return myObj.toString();
    }

    public static String getValueOfColumnId(Object object, Class clazz){
        Object myObj = null;
        Class<?> superclass = clazz.getSuperclass();
        //while (!superclass.equals(Object.class)) {
            MappedSuperclass superclassAno = superclass.getAnnotation(MappedSuperclass.class);
            if (superclassAno != null) {
                Field[] fds = superclass.getDeclaredFields();
                for(Field f : fds){
                    if(f.getName().equals("id")){
                        f.setAccessible(true);
                        try{
                            myObj = f.get(object);
                            //System.out.println(myObj.toString());
                        }
                        catch (IllegalAccessException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        //   superclass = superclass.getSuperclass();
        //}
        return myObj.toString();
    }

    public static String makeSaveQuery( Object object, Class clazz ){
        return "INSERT INTO " + getTableName(clazz) + " VALUES (" + getValueOfColumnId(object, object.getClass())
                + ",'" + getValueOfColumnName(object) + "'," + getValueOfColumnAge(object) + " )";
    }
}
