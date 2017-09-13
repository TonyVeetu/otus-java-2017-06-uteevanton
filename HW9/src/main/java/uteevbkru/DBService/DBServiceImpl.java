package uteevbkru.DBService;

import uteevbkru.dataset.DataSet;
import uteevbkru.dataset.UsersDAO;
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

    public DBServiceImpl(Connection connection){
        this.connection = connection;
        dao = new UsersDAO(connection);
    }

    @Override
    public void save(UsersDataSet user){
        try {
            dao.save(user);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public UsersDataSet load(Long id){
        UsersDataSet user = null;

        try {
            user = dao.load(id, UsersDataSet.class);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

}
