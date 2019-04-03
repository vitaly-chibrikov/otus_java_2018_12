package ru.otus.connectionPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class Demo {
    private static final String URL = "jdbc:postgresql://localhost:5432/otus";
    private final DataSource dataSource;

    public static void main(String[] args) throws SQLException {
        final Demo demo = new Demo();
        demo.createTable();
        demo.insertRecords();
        demo.useConnectionPool();
    }

    public Demo() {
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername("otus_user");
        config.setPassword("otus_password");
        config.setConnectionTimeout(3000); //ms
        config.setIdleTimeout(60000); //ms
        config.setMaxLifetime(600000);//ms
        config.setAutoCommit(false);
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);
        config.setPoolName("DemoHiPool");
        config.setRegisterMbeans(true);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    private void createTable() throws SQLException {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement pst = connection.prepareStatement("CREATE TABLE otus.test(id INT, name VARCHAR(50))")) {
            pst.executeUpdate();
            connection.commit();
        }
    }

    private void insertRecords() throws SQLException {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement pst = connection.prepareStatement("INSERT INTO otus.test(id, name) VALUES (?, ?)")) {
            final Savepoint savePoint = connection.setSavepoint("savePointName");
            try {
                int rowCount = 0;
                for (int idx = 0; idx < 100; idx++) {
                    pst.setInt(1, idx);
                    pst.setString(2, "NameValue_" + idx);
                    rowCount += pst.executeUpdate();
                }
                connection.commit();
                System.out.println("inserted rowCount:" + rowCount);
            } catch (SQLException ex) {
                connection.rollback(savePoint);
                System.out.println(ex.getMessage());
            }
        }
    }

    private void useConnectionPool() {
        new Thread(this::doSelect).start();
        new Thread(this::doSelect).start();
        new Thread(this::doSelect).start();
    }

    private void doSelect() {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement pst = connection.prepareStatement("SELECT count(*) AS counter FROM otus.test")) {
            while (true) {
                try (final ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        System.out.println(Thread.currentThread().getName() + "  " + rs.getString("counter"));
                    }
                }
                Thread.sleep(3_000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
