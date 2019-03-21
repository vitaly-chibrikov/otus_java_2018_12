package ru.otus.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserPreparedDAO implements UserDAO {
    private final Connection connection;

    public UserPreparedDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) throws SQLException {
        try (final PreparedStatement statement = connection.prepareStatement("INSERT INTO otus.otus_user (user_name, age) VALUES (upper(?), ?);")) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.executeUpdate();
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        try (final PreparedStatement statement = connection.prepareStatement("SELECT id, user_name, age FROM otus.otus_user WHERE id = ?;")) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            return UserDAO.extract(resultSet);
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        try (final PreparedStatement statement = connection.prepareStatement("SELECT id, user_name, age FROM otus.otus_user;")) {
            final ResultSet resultSet = statement.executeQuery();
            return UserDAO.extractList(resultSet);
        }
    }

    @Override
    public void update(User user) throws SQLException {
        try (final PreparedStatement statement = connection.prepareStatement("UPDATE otus.otus_user SET user_name = ?, age = ? WHERE id = ?;")) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (final PreparedStatement statement = connection.prepareStatement("DELETE FROM otus.otus_user WHERE id = ?;")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
