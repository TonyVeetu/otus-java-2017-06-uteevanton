package uteevbkru.main;

import uteevbkru.dataset.UsersDataSet;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by anton on 25.08.17.
 */
public class TableHelper {

    private  Class clazz;

    public TableHelper(Class clazz){
        this.clazz = clazz;
    }

    public String getTableName(){
        Annotation an = clazz.getDeclaredAnnotation(Table.class);
        if(an != null) {
            Table anTable = (Table) an;
            if(anTable != null)
                return anTable.name();
        }
        //else
         //   throw new IllegalArgumentException();
                //TODO
        return new String("d");
    }


    public static String getNameUser(){
        Field[] fds = UsersDataSet.class.getDeclaredFields();
        //Field[] fds = clazz.getDeclaredFields();
        ArrayList<String> namesColumn = new ArrayList<>();
        for (Field f : fds) {
            Column annColumn = f.getAnnotation(Column.class);

            System.out.println(f.getName() + "; " + annColumn.name());
            namesColumn.add(annColumn.name());
        }
        return new String("s");
    }


    //TODO
    //public String getAgeUser(){

    //}
}
