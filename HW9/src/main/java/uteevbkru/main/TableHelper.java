package uteevbkru.main;

import javax.persistence.Table;
import java.lang.annotation.Annotation;

/**
 * Created by anton on 25.08.17.
 */
public class TableHelper {

    private  Class clazz;

    public TableHelper(Class clazz){
        this.clazz = clazz;
    }

    public String getTableName() throws NullPointerException{
        Annotation an = clazz.getDeclaredAnnotation(Table.class);
        if(an != null) {
            Table anTable = (Table) an;
            if(anTable != null)
                return anTable.name();
            throw new NullPointerException();
        }
        else
            throw new NullPointerException();
    }

    /**_____May_be_for_FUTURE_methods_____*/
//        Field[] fds = clazz.getDeclaredFields();
//        ArrayList<String> namesColumn = new ArrayList<>();
//
//        for(Field f : fds) {
//            Column annColumn = f.getAnnotation(Column.class);
//
//            System.out.println(f.getName() + "; " + annColumn.name());
//            namesColumn.add(annColumn.name());
//        }
}
