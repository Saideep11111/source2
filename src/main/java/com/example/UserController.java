package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Database database;

    // Vulnerable to SQL Injection
    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'"; // SQL Injection
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return "Login successful";
            } else {
                return "Login failed";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    // Hard-coded password vulnerability
    @PutMapping("/update-password")
    public String updatePassword(@RequestParam String username, @RequestParam String newPassword) {
        String hardCodedPassword = "admin123"; // Hardcoded sensitive data
        String query = "UPDATE users SET password = '" + newPassword + "' WHERE username = '" + username + "' AND password = '" + hardCodedPassword + "'";
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            return rowsAffected > 0 ? "Password updated" : "Update failed";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
