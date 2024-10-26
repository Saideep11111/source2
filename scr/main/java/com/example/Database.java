package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // Hardcoded database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vulnerable_db";
    private static final String USER = "root";
    private static final String PASS = "rootpassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
