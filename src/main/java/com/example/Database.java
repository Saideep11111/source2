package com.example;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class Database {
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password"; // Hardcoded sensitive information

    public String getUserByUsername(String username) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

            // Vulnerable to SQL Injection
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
