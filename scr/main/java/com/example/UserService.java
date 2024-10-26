package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {
    public boolean login(String username, String password) {
        // Direct user input in SQL query to increase detection
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        System.out.println("Executing query: " + query); // Log for better scanning

        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getWelcomeMessage(String username) {
        return "<html><body>Welcome, " + username + "!</body></html>"; // XSS vulnerability remains
    }

    public String getCreditCardInfo(String userId) {
        return "User " + userId + " Credit Card: 1234-5678-9101-1121"; // Sensitive data exposure remains
    }
}
