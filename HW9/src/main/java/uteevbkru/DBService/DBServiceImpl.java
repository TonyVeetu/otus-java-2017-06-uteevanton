package uteevbkru.DBService;

import uteevbkru.dataset.UsersDataSet;
import uteevbkru.executor.TExecutor;
import uteevbkru.main.TableHelper;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by anton on 25.08.17.
 */
public class DBServiceImpl implements DBService {
    private UsersDAO dao;
    private Connection connection;
    private  TableHelper helper;

    public DBServiceImpl(Connection connection, Class clazz){
        this.connection = connection;
        helper = new TableHelper(clazz);
        dao = new UsersDAO();
    }

    @Override
    public void save(UsersDataSet user){
        try {
            dao.save(user, helper.getTableName());
        }
        catch (SQLException e){
            e.printStackTrace();
            //TODO logirovat use log4!!!
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public UsersDataSet load(long id){
        UsersDataSet user = null;
        try {
            user = dao.load(id, helper.getTableName());
        }
        catch (SQLException e){
            e.printStackTrace();
            //TODO logirovat use log4!!!
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return user;
    }

    private class UsersDAO {
        private TExecutor exec;

        private UsersDAO(){
            exec = new TExecutor(connection);
        }

        private UsersDataSet load(long id, String table) throws SQLException {
            return exec.execQuery("SELECT * FROM "+ table + " WHERE id=" + id, result -> {
                //TODO не получается сделать проверку на NULL use if(result.next()){...}!!!
                result.next();
                return new UsersDataSet(result.getLong(1), result.getString(2), result.getInt(3));

            });
        }

        private int save(UsersDataSet user, String table) throws SQLException {
            System.out.println("UserDAO:save - " + "insert into users_hw9 (user_name) values (" + user.getId() + ",'" + user.getName() + "'," + user.getAge() + " )");
            return exec.execUpdate("INSERT INTO " + table + " VALUES (" + user.getId() + ",'" + user.getName() + "'," + user.getAge() + " )");
        }
    }
}
