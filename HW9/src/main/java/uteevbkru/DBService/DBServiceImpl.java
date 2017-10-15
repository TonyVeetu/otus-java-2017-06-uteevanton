package uteevbkru.DBService;

import uteevbkru.dao.UserDAO;
import uteevbkru.model.UserDataSet;

import java.sql.Connection;
import java.sql.SQLException;

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
        user = dao.load(id, UserDataSet.class);
        return user;
    }

}
