package uteevbkru.DBService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import uteevbkru.dataset.AddressDataSet;
import uteevbkru.dataset.PhoneDataSet;
import uteevbkru.dataset.UsersDataSet;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

/**
 * Created by anton on 27.08.17.
 */
public class DBServiceImplHibernate implements DBService {
    private final SessionFactory sessionFactory;

    public DBServiceImplHibernate() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UsersDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");////org.hibernate.dialect.MySQL5Dialect
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");////com.mysql.cj.jdbc.Driver
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/otushw10");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "12345");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        configuration.setProperty("hibernate.jdbc.time_zone", "UTC");

        sessionFactory = createSessionFactory(configuration);

    }

    public DBServiceImplHibernate(Configuration configuration) {
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public void save(UsersDataSet user){
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(user);
        }
    }

    public UsersDataSet read(long id){
        try (Session session = sessionFactory.openSession()){
            return session.get(UsersDataSet.class, id);
        }
    }

    public String getLocalStatus() {
        return runInSession(session -> {
            return session.getTransaction().getStatus().name();
        });
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    public void shutdown() {
        sessionFactory.close();
    }


}
