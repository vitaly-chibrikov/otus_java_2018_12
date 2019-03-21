package ru.otus.base;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class DBServiceImpl implements DBService {
    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS otus.otus_user (\n" +
            "  id        BIGSERIAL NOT NULL PRIMARY KEY,\n" +
            "  user_name VARCHAR(255),\n" +
            "  age       INTEGER\n" +
            ");";
    private static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS otus.otus_user;";

    private final Connection connection;

    public DBServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getMetaData() throws SQLException {
        final StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Autocommit: " + connection.getAutoCommit());
        final DatabaseMetaData metaData = connection.getMetaData();
        joiner.add("DB name: " + metaData.getDatabaseProductName());
        joiner.add("DB version: " + metaData.getDatabaseProductVersion());
        joiner.add("Driver name: " + metaData.getDriverName());
        joiner.add("Driver version: " + metaData.getDriverVersion());
        joiner.add("JDBC version: " + metaData.getJDBCMajorVersion() + '.' + metaData.getJDBCMinorVersion());
        return joiner.toString();
    }

    @Override
    public void prepareTables() throws SQLException {
        try (final Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_USER);
        }
    }

    @Override
    public void deleteTables() throws SQLException {
        try (final Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_TABLE_USER);
        }
    }
}
