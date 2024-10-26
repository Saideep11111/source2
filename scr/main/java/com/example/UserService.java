package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {
    // SQL Injection vulnerability (Vulnerability #3)
    public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        
        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cross-Site Scripting (XSS) vulnerability (Vulnerability #4)
    public String getWelcomeMessage(String username) {
        return "<html><body>Welcome, " + username + "!</body></html>";
    }

    // Sensitive data exposure (Vulnerability #5)
    public String getCreditCardInfo(String userId) {
        return "User " + userId + " Credit Card: 1234-5678-9101-1121";
    }
}
