package ru.otus;

import ru.otus.base.DBService;
import ru.otus.base.DBServiceImpl;
import ru.otus.dbcommon.ConnectionHelper;
import ru.otus.user.User;
import ru.otus.user.UserDAO;
import ru.otus.user.UserSimpleDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcMain {
    public static void main(String[] args) {
//        final DataSource dataSource = ConnectionPoolHelper.getDataSource();
//        try (final Connection connection = dataSource.getConnection()) {
        try (final Connection connection = ConnectionHelper.getPostgresqlConnection()) {
            final DBService dbService = new DBServiceImpl(connection);
            dbService.prepareTables();

            System.out.println(dbService.getMetaData());

            final UserDAO dao = getUserDAO(connection);
            dao.create(new User("user 1", 1));
            dao.create(new User("user 2", 2));
            dao.create(new User("user 3", 3));

            dao.getAll().forEach(System.out::println);

            final User user1 = dao.getById(1);
            System.out.println(user1.getName());

            user1.setName("new name");
            dao.update(user1);

            dao.delete(2);

            dao.getAll().forEach(System.out::println);

            dbService.deleteTables();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static UserDAO getUserDAO(Connection connection) {
        return new UserSimpleDAO(connection);
//        return new UserPreparedDAO(connection);
//        return new UserExecutorDAO(connection);
//        return new UserPreparedExecutorDAO(connection);
    }
}
