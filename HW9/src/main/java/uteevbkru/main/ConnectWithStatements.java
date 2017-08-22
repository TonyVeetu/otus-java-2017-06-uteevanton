package uteevbkru.main;

import uteevbkru.executor.SimpleExecutor;
import uteevbkru.handlers.ResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectWithStatements {

    public static void example() {
        Connection connection = ConnectionHelper.getConnection();
        SimpleExecutor exec = new SimpleExecutor(connection);
        try {
            //exec.execUpdate("create table users (id bigint auto_increment, user_name varchar(256), primary key (id))");
            //System.out.println("Table created");
            //exec.execUpdate("insert into users (user_name) values ('tully25')");
            //System.out.println("User added");

            //with inner class
            exec.execQuery("select users_hw9_name from users_hw9 where users_hw9_id=1", new ResultHandlerGetName());

            //with anonymous class

            exec.execQuery("select users_hw9_name from users_hw9 where users_hw9_id=4",
                    new ResultHandler() {
                        @Override
                        public void handle(ResultSet result) throws SQLException {
                            result.next();
                            System.out.println("Read user: " + result.getString("users_hw9_name"));
                        }
                    }
            );

            //with lambda
            exec.execQuery("select users_hw9_name from users_hw9 where users_hw9_id=23",
                    result -> {
                        result.next();
                        System.out.println("Read user: " + result.getString("users_hw9_name"));
                    });

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //exec.execUpdate("drop table users");
                //System.out.println("Done!");

                connection.close();
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
        }
    }

    static class ResultHandlerGetName implements ResultHandler {
        public void handle(ResultSet result) throws SQLException {
            result.next();
            System.out.println("Read user: " + result.getString("users_hw9_name"));
        }
    }


}
