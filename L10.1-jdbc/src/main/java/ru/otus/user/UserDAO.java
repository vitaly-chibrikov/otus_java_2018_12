package ru.otus.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO {
    static User create(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("id"), resultSet.getString("user_name"), resultSet.getInt("age"));
    }

    static User extract(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        return create(resultSet);
    }

    static List<User> extractList(ResultSet resultSet) throws SQLException {
        final List<User> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(create(resultSet));
        }
        return result;
    }

    void create(User user) throws SQLException;

    User getById(int id) throws SQLException;

    List<User> getAll() throws SQLException;

    void update(User user) throws SQLException;

    void delete(int id) throws SQLException;
}
