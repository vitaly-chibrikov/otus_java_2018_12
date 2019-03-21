package ru.otus.base;

import java.sql.SQLException;

public interface DBService {
    String getMetaData() throws SQLException;

    void prepareTables() throws SQLException;

    void deleteTables() throws SQLException;
}
