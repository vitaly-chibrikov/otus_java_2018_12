package ru.otus.dbcommon;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.sql.DataSource;

public class ConnectionPoolHelper {
    public static DataSource getDataSource() {
        final String connString = "jdbc:postgresql://" +    // db type
                "localhost:" +                              // host name
                "5432/" +                                   // port
                "otus?" +                                   // db name
                "user=otus_user&" +                         // login
                "password=otus_password";                   // password

        final ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connString, null);
        final PoolableConnectionFactory poolableFactory = new PoolableConnectionFactory(connectionFactory, null);
        final ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableFactory);
        poolableFactory.setPool(connectionPool);
        return new PoolingDataSource<>(connectionPool);
    }
}
