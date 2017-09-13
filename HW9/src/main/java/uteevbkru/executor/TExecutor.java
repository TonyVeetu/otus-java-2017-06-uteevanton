package uteevbkru.executor;

import uteevbkru.handlers.TResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TExecutor extends SimpleExecutor {
    private final Connection connection;

    public TExecutor(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    public <T> T execQuery(String query, TResultHandler<T> handler){
        try(Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery(query)) {
            return handler.handle(resultSet);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }

        return null;
    }

    public int execUpdate(String query) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            return stmt.getUpdateCount();
        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public void execAllPrint(String query) throws SQLException {
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            int column = rs.getMetaData().getColumnCount();
            System.out.println("Count of column: "+column);
            while(rs.next()){
                for(int i = 1; i <= column; i++){
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        }
    }

}
