package ru.otus.HW06;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mix on 07.04.2018.
 */
public class QueryHelper {

    private final Connection connection;

    public QueryHelper(Connection connection) {
        this.connection = connection;
    }

    public long insert(String sql) throws SQLException {
        Statement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.executeUpdate(sql);

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }

    public void truncate(String tableName) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("TRUNCATE " + tableName);
    }

    public ResultSet select(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }

}
