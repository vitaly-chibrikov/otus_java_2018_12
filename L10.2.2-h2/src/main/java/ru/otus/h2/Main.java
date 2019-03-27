package ru.otus.h2;

import org.h2.tools.Server;

import java.sql.SQLException;

/**
 * http://localhost:8082
 *
 * CREATE TABLE USERS(
 *    ID   INT              NOT NULL,
 *    NAME VARCHAR (20)     NOT NULL,
 *    AGE  INT              NOT NULL,
 * );
 *
 * insert into users (id, name, age) values (0, 'sully', 23);
 */

public class Main {
    public static void main(String[] args) throws SQLException {
        Server.createWebServer("-web","-webAllowOthers","-webPort","8082").start();
        ConnectionHelper.example();
    }
}
