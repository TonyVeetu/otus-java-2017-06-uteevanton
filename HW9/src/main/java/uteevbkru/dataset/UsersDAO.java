package uteevbkru.dataset;

import uteevbkru.executor.SimpleExecutor;
import uteevbkru.executor.TExecutor;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {

    private Connection connection;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    public UsersDataSet get(long id, String table) throws SQLException {
        TExecutor exec = new TExecutor(connection);
        return exec.execQuery("select * from "+ table + " where id=" + id, result -> {
            //Нужно ли ее делать??
            //TODO как можно сделать проверку на NULL?
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2), result.getInt(3));
        });
    }

    public int put(UsersDataSet user, String table) throws SQLException {
        SimpleExecutor exec = new SimpleExecutor(connection);
        System.out.println("UserDAO:put" + "insert into users_hw9 (user_name) values (" + user.getId() + ",'" + user.getName() + "'," + user.getAge() + " )");
        return exec.execUpdate("insert into " + table + " values (" + user.getId() + ",'" + user.getName() + "'," + user.getAge() + " )");
    }
}
