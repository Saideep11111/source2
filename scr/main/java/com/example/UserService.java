package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {
    public boolean login(String username, String password) {
        // Direct SQL Injection vulnerability
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        System.out.println("Executing SQL Query: " + query); // Log for clarity

        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // XSS vulnerability with unfiltered user input
    public String getWelcomeMessage(String username) {
        return "<html><body>Welcome, " + username + "!</body></html>";
    }
}
