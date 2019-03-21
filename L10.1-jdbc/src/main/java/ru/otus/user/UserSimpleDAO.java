package ru.otus.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserSimpleDAO implements UserDAO {
    private final Connection connection;

    public UserSimpleDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) throws SQLException {
        try (final Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO otus.otus_user (user_name, age) VALUES ('" + user.getName() + "', " + user.getAge() + ");");
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT id, user_name, age FROM otus.otus_user WHERE id = " + id + ';');
            return UserDAO.extract(resultSet);
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT id, user_name, age FROM otus.otus_user;");
            return UserDAO.extractList(resultSet);
        }
    }

    @Override
    public void update(User user) throws SQLException {
        try (final Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE otus.otus_user SET user_name = '" + user.getName() + "', age = " + user.getAge() + " WHERE id = " + user.getId() + ';');
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (final Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM otus.otus_user WHERE id = " + id + ';');
        }
    }
}
