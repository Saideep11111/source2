package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {
    // SQL Injection with directly concatenated user input
    public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        System.out.println("Executing query: " + query);

        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cross-Site Scripting (XSS) vulnerability
    public String getWelcomeMessage(String username) {
        // Unescaped user input directly in HTML
        return "<html><body>Welcome, " + username + "!</body></html>";
    }
}
