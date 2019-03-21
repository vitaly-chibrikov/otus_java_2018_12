package ru.otus.dbcommon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    public static Connection getPostgresqlConnection() throws SQLException {
//        final Driver driver = new org.postgresql.Driver();
//        final Driver driver = (Driver) Class.forName("org.postgresql.Driver").getConstructor().newInstance();
//        DriverManager.registerDriver(driver);

        final String connString = "jdbc:postgresql://" +    // db type
                "localhost:" +                              // host name
                "5432/" +                                   // port
                "otus?" +                                   // db name
                "user=otus_user&" +                         // login
                "password=otus_password";                   // password

        return DriverManager.getConnection(connString);
    }

    public Connection getMysqlConnection() throws SQLException {
//        final Driver driver = new com.mysql.cj.jdbc.Driver();
//        final Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
//        DriverManager.registerDriver(driver);

        final String connString = "jdbc:mysql://" +         // db type
                "localhost:" +                              // host name
                "3306/" +                                   // port
                "otus?" +                                   // db name
                "user=otus_user&" +                         // login
                "password=otus_password&" +                 // password
                "useSSL=false";                             // do not use Secure Sockets Layer

        return DriverManager.getConnection(connString);
    }
}
