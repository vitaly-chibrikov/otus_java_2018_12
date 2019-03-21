package ru.otus.user;

import ru.otus.executors.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserExecutorDAO implements UserDAO {
    private final Connection connection;

    public UserExecutorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) throws SQLException {
        Executor.update(connection, "INSERT INTO otus.otus_user (user_name, age) VALUES ('" + user.getName() + "', " + user.getAge() + ");");
    }

    @Override
    public User getById(int id) throws SQLException {
        return Executor.query(connection, "SELECT id, user_name, age FROM otus.otus_user WHERE id = " + id + ';', UserDAO::extract);
    }

    @Override
    public List<User> getAll() throws SQLException {
        return Executor.query(connection, "SELECT id, user_name, age FROM otus.otus_user;", UserDAO::extractList);
    }

    @Override
    public void update(User user) throws SQLException {
        Executor.update(connection, "UPDATE otus.otus_user SET user_name = '" + user.getName() + "', age = " + user.getAge() + " WHERE id = " + user.getId() + ';');
    }

    @Override
    public void delete(int id) throws SQLException {
        Executor.update(connection, "DELETE FROM otus.otus_user WHERE id = " + id + ';');
    }
}
