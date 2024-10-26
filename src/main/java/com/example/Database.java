package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String url = "jdbc:mysql://localhost:3306/testdb";
    private final String user = "root";
    private final String password = "password"; // hardcoded password

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
