package uteevbkru.backend.db.dbService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by anton on 14.09.17.
 */
public class HibernateService {
    private static volatile HibernateService instance;
    private SessionFactory sessionFactory;

    private HibernateService() {
        sessionFactory = HibernateHelper.createSessionFactory();
    }

    public static HibernateService getInstance() {
        if (instance == null) {
            synchronized (HibernateService.class) {
                if (instance == null) {
                    instance = new HibernateService();
                }
            }
        }
        return instance;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void close() {
        sessionFactory.close();
    }
}
