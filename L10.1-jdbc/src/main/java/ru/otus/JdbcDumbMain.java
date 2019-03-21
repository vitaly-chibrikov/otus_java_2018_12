package ru.otus;

import ru.otus.dbcommon.ConnectionHelper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDumbMain {
    public static void main(String[] args) {
        try (final Connection connection = ConnectionHelper.getPostgresqlConnection();
             final Statement statement = connection.createStatement()) {

            System.out.println("Autocommit: " + connection.getAutoCommit());
            final DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("DB name: " + metaData.getDatabaseProductName());
            System.out.println("DB version: " + metaData.getDatabaseProductVersion());
            System.out.println("Driver name: " + metaData.getDriverName());
            System.out.println("Driver version: " + metaData.getDriverVersion());
            System.out.println("JDBC version: " + metaData.getJDBCMajorVersion() + '.' + metaData.getJDBCMinorVersion());

            final String createTableUser = "CREATE TABLE IF NOT EXISTS otus.otus_user (id BIGSERIAL NOT NULL PRIMARY KEY, user_name VARCHAR(255), age INTEGER);";
            statement.executeUpdate(createTableUser);

            statement.executeUpdate("INSERT INTO otus.otus_user (user_name, age) VALUES ('user 1', 1);");
            statement.executeUpdate("INSERT INTO otus.otus_user (user_name, age) VALUES ('user 2', 2);");
            statement.executeUpdate("INSERT INTO otus.otus_user (user_name, age) VALUES ('user 3', 3);");

            ResultSet resultSet = statement.executeQuery("SELECT id, user_name, age FROM otus.otus_user;");
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("id") + ", '" + resultSet.getString("user_name") + "', " + resultSet.getInt("age"));
            }

            resultSet = statement.executeQuery("SELECT user_name FROM otus.otus_user WHERE id = 1;");
            resultSet.next();
            System.out.println(resultSet.getString("user_name"));

            statement.executeUpdate("UPDATE otus.otus_user SET user_name = 'new name' WHERE id = 1");

            statement.executeUpdate("DELETE FROM otus.otus_user WHERE id = 2");

            resultSet = statement.executeQuery("SELECT id, user_name, age FROM otus.otus_user;");
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("id") + ", '" + resultSet.getString("user_name") + "', " + resultSet.getInt("age"));
            }

            statement.executeUpdate("DROP TABLE IF EXISTS otus.otus_user;");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
