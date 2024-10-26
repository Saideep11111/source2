package com.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        UserService userService = new UserService();
        
        // Hardcoded credentials (Vulnerability #1)
        String username = "admin";
        String password = "password123";
        userService.login(username, password);
        
        // Unsafe deserialization (Vulnerability #2)
        UnsafeSerialization unsafeSerialization = new UnsafeSerialization();
        try {
            unsafeSerialization.deserializeObject("serialized-object.bin");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
