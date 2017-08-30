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

    public <T> T execQuery(String query, TResultHandler<T> handler) throws SQLException {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            return handler.handle(result);
        }
    }

    public int execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
            return stmt.getUpdateCount();
        }
    }

}