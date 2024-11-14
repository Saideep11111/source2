package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Database database;

    // Insecure login method with SQL injection vulnerability
    @GetMapping("/insecure-login")
    public String insecureLogin(@RequestParam String username, @RequestParam String password) {
        // Directly concatenating parameters into the SQL query allows for SQL injection
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
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

    // Adding hardcoded password vulnerability
    @PostMapping("/admin-login")
    public String adminLogin(@RequestParam String username, @RequestParam String password) {
        // Hardcoded credentials, which are a security risk
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "Admin login successful";
        } else {
            return "Admin login failed";
        }
    }

    // Method with open redirect vulnerability
    @GetMapping("/redirect")
    public String redirectUser(@RequestParam String targetUrl) {
        // Redirecting to a URL based on untrusted user input
        return "Redirecting to: <a href=\"" + targetUrl + "\">" + targetUrl + "</a>";
    }

    // Insecure password update method with no password strength check
    @PutMapping("/insecure-update-password")
    public String insecureUpdatePassword(@RequestParam String username, @RequestParam String newPassword) {
        String query = "UPDATE users SET password = '" + newPassword + "' WHERE username = '" + username + "'";
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);
            return rowsAffected > 0 ? "Password updated insecurely" : "Update failed";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
