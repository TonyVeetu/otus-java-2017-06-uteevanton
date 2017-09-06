package uteevbkru.main;

import org.omg.CORBA.DynAnyPackage.Invalid;
import uteevbkru.dataset.UsersDataSet;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.InvalidClassException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class TableHelper {

    private  Class clazz;
    private static String NAME_USER = "users_hw9_name";
    private static String AGE_USER = "users_hw9_age";

    public TableHelper(Class clazz){
        this.clazz = clazz;
    }


    //TODO не понимаю как сдесь правильно бросать исключение!
    public String getTableName(){
        String a = null;
        Annotation an = clazz.getDeclaredAnnotation(Table.class);
        if(an != null) {
            Table anTable = (Table) an;
            if(anTable != null)
                return anTable.name();
            else
                throw new IllegalArgumentException();
        }
        else
            throw new IllegalArgumentException();
    }


    public String getNameUser(){
        String a = null;
        Field[] fds = clazz.getDeclaredFields();
        if(fds != null) {
            for (Field f : fds) {
                Column annColumn = f.getAnnotation(Column.class);
                    if(annColumn.name().equals(NAME_USER)){
                        System.out.println("name");
                        return annColumn.name();
                    }
            }
        }
        return a;
    }


    public String getAgeUser(){
        String a = null;
        Field[] fds = clazz.getDeclaredFields();
        if(fds != null) {
            for (Field f : fds) {
                Column annColumn = f.getAnnotation(Column.class);
                if(annColumn.name().equals(AGE_USER)){
                    System.out.println("age");
                    return annColumn.name();
                }
            }
        }
        return a;
    }
}
