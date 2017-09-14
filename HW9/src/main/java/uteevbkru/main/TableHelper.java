package uteevbkru.main;

import uteevbkru.dataset.UsersDataSet;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class TableHelper {
    private  Class clazz;

    public TableHelper(Class clazz){
        this.clazz = clazz;
    }

    public static String getTableName(Class clazz){
        String name = null;
        Annotation an = clazz.getDeclaredAnnotation(Table.class);
        if(an != null) {
            Table anTable = (Table) an;
            name = anTable.name();
        }
        return name;
    }

    public static String getNameOfColumnName(){
        String name = null;
        Field[] fds = UsersDataSet.class.getDeclaredFields();
        for (Field f : fds) {
            Column annColumn = f.getAnnotation(Column.class);
                if(f.getName().equals("name")) {
                    name = annColumn.name();
                }
        }
        return name;
    }

    public static String getNameOfColumnAge(){
        String name = null;
        Field[] fds = UsersDataSet.class.getDeclaredFields();
        for (Field f : fds) {
            Column annColumn = f.getAnnotation(Column.class);
            if(f.getName().equals("age")) {
                name = annColumn.name();
            }
        }
        return name;
    }

    public static String getValueOfColumnName(Object object){
        Object myObj = null;
        Field[] fds = UsersDataSet.class.getDeclaredFields();
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
        Field[] fds = UsersDataSet.class.getDeclaredFields();
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
        return "INSERT INTO " + getTableName(clazz) + " VALUES (" + getValueOfColumnId(object, object.getClass()) + ",'" + getValueOfColumnName(object) + "'," + getValueOfColumnAge(object) + " )";
    }
}
