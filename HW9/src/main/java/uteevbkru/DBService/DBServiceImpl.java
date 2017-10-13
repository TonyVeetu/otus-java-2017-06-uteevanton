package uteevbkru.DBService;

import uteevbkru.dao.UserDAO;
import uteevbkru.model.UserDataSet;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by anton on 25.08.17.
 */
public class DBServiceImpl implements DBService {
    private UserDAO dao;
    private Connection connection;

    public DBServiceImpl(Connection connection){
        this.connection = connection;
        dao = new UserDAO(connection);
    }

    @Override
    public void save(UserDataSet user){
        try {
            dao.save(user);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public UserDataSet load(Long id){
        UserDataSet user = null;

        try {
            user = dao.load(id, UserDataSet.class);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

}
