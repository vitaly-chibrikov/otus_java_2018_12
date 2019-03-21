package ru.otus.user;

import ru.otus.executors.Executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserPreparedExecutorDAO implements UserDAO {
    private final Connection connection;

    public UserPreparedExecutorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement("INSERT INTO otus.otus_user (user_name, age) VALUES (?, ?);");
        statement.setString(1, user.getName());
        statement.setInt(2, user.getAge());
        Executor.updatePrepared(statement);
    }

    @Override
    public User getById(int id) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement("SELECT id, user_name, age FROM otus.otus_user WHERE id = ?;");
        statement.setInt(1, id);
        return Executor.queryPrepared(statement, UserDAO::extract);
    }

    @Override
    public List<User> getAll() throws SQLException {
        final PreparedStatement statement = connection.prepareStatement("SELECT id, user_name, age FROM otus.otus_user;");
        return Executor.queryPrepared(statement, UserDAO::extractList);
    }

    @Override
    public void update(User user) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement("UPDATE otus.otus_user SET user_name = ?, age = ? WHERE id = ?;");
        statement.setString(1, user.getName());
        statement.setInt(2, user.getAge());
        statement.setLong(3, user.getId());
        Executor.updatePrepared(statement);
    }

    @Override
    public void delete(int id) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement("DELETE FROM otus.otus_user WHERE id = ?;");
        statement.setInt(1, id);
        Executor.updatePrepared(statement);
    }
}
