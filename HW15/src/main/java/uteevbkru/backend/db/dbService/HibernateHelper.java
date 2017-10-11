package uteevbkru.backend.db.dbService;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import uteevbkru.backend.db.dataSet.*;

/**
 * Created by anton on 14.09.17.
 */
public class HibernateHelper {
    private HibernateHelper() {
    }

    private static Configuration makeHibernateConfiguration() {
        Configuration result = new Configuration();
        result.addAnnotatedClass(DataSet.class);
        result.addAnnotatedClass(UserDataSet.class);
        result.addAnnotatedClass(AddressDataSet.class);
        result.addAnnotatedClass(PhoneDataSet.class);

        result.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");////org.hibernate.dialect.MySQL5Dialect
        result.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");////com.mysql.cj.jdbc.Driver
        result.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/otushw10");
        result.setProperty("hibernate.connection.username", "postgres");
        result.setProperty("hibernate.connection.password", "12345");
        result.setProperty("hibernate.show_sql", "true");
        result.setProperty("hibernate.hbm2ddl.auto", "create");
        result.setProperty("hibernate.connection.useSSL", "false");
        result.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        result.setProperty("hibernate.jdbc.time_zone", "UTC");

        return result;
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = makeHibernateConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}
