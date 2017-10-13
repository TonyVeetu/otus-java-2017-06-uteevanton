package uteevbkru.help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by anton on 18.08.17.
 */
public class ConnectionHelper {

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());

            String url = "jdbc:postgresql://" +       //db type
                    "localhost:" +               //host name
                    "5432/" +                    //port
                    "OtusHW9";                  //db name

            Properties prop = new Properties();
            prop.setProperty("user", "postgres");
            prop.setProperty("password", "12345");
            prop.setProperty("ssl", "false");

            return DriverManager.getConnection(url, prop);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getInformationFromDB() {
        try (Connection connection = getConnection()) {
            System.out.println("Connected to: " + connection.getMetaData().getURL());
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
