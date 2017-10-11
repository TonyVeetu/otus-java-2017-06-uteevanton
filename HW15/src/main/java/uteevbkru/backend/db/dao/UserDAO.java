package uteevbkru.backend.db.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import uteevbkru.backend.db.dataSet.UserDataSet;
import uteevbkru.backend.db.dbService.HibernateService;

/**
 * Created by anton on 14.09.17.
 */
public class UserDAO {

    private HibernateService hibernateService = HibernateService.getInstance();

    public UserDataSet getUser(Long id) {
        Session session = hibernateService.getSession();
        UserDataSet result = session.get(UserDataSet.class, id);
        session.close();
        return result;
    }

    public void saveUser(UserDataSet user) {
        Session session = hibernateService.getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }

    public void close(){
        hibernateService.close();
    }
}
