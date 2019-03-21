package ru.otus.executors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    public static <T> T query(Connection connection, String query, TResultHandler<T> handler) throws SQLException {
        try (final Statement statement = connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            return handler.handle(result);
        }
    }

    public static void update(Connection connection, String update) throws SQLException {
        try (final Statement statement = connection.createStatement()) {
            statement.executeUpdate(update);
        }
    }

    public static <T> T queryPrepared(PreparedStatement statement, TResultHandler<T> handler) throws SQLException {
        try (statement) {
            final ResultSet resultSet = statement.executeQuery();
            return handler.handle(resultSet);
        }
    }

    public static void updatePrepared(PreparedStatement statement) throws SQLException {
        try (statement) {
            statement.executeUpdate();
        }
    }
}
