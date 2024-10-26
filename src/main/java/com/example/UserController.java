package com.example;

import java.sql.Connection; // Add this import
import java.sql.DriverManager; // Add this import
import java.sql.SQLException; // Add this import
import java.sql.Statement; // Add this import
import java.sql.ResultSet; // Add this import
import java.util.ArrayList; // If you're using this
import java.util.List; // If you're using this

public class UserController {

    public void createUser(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/mydb"; // Example DB URL
        String user = "root"; // Example user
        String pass = "password"; // Example password

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement stmt = connection.createStatement()) {

            String sql = "INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getUsers() {
        List<String> users = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/mydb"; // Example DB URL
        String user = "root"; // Example user
        String pass = "password"; // Example password

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT username FROM users")) {

            while (rs.next()) {
                users.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
