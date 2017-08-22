package uteevbkru.executor;

import uteevbkru.dataset.UsersDAO;
import uteevbkru.dataset.UsersDataSet;
import uteevbkru.main.ConnectionHelper;

import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by anton on 20.08.17.
 */
public class LoadSaveExecutor {

    public static void save(UsersDataSet user1, Class clazz){
        String nameTable = getTableName(clazz);
        Connection connection = ConnectionHelper.getConnection();

        int count_from_db = 0;
        try {
            UsersDAO usersDAO = new UsersDAO(connection);
            count_from_db = usersDAO.put(user1, nameTable);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Put " + count_from_db + " user(s) int DB!!");
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

    public static UsersDataSet load(long id, Class clazz){
        String nameTable = getTableName(clazz);
        Connection connection = ConnectionHelper.getConnection();

        UsersDataSet result = null;
        try {
            UsersDAO userDAO = new UsersDAO(connection);
            result = userDAO.get(id, nameTable);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    private static String getTableName(Class clazz){
        Annotation an = clazz.getDeclaredAnnotation(Table.class);
        Table anTable = (Table)an;
        return anTable.name();
    }
}
