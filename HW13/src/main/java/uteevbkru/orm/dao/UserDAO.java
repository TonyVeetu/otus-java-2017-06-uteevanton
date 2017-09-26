package uteevbkru.orm.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import uteevbkru.orm.dataSet.UserDataSet;
import uteevbkru.orm.dbService.HibernateService;

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
