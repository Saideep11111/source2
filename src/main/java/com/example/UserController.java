package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Database database;

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Retrieve password (vulnerable to timing attacks and SQL injection)
        String storedPassword = database.getUserByUsername(username);

        if (storedPassword != null && storedPassword.equals(password)) {
            return "Login successful!";
        }
        return "Invalid credentials";
    }

    @PostMapping("/update")
    public String updatePassword(@RequestParam String username, @RequestParam String newPassword) {
        // Directly expose update method without validation (vulnerable to unauthorized access)
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "admin", "password");
            Statement statement = connection.createStatement();

            // Vulnerable to SQL Injection
            String updateQuery = "UPDATE users SET password = '" + newPassword + "' WHERE username = '" + username + "'";
            statement.executeUpdate(updateQuery);
            return "Password updated";
        } catch (Exception e) {
            return "Error updating password";
        }
    }
}
