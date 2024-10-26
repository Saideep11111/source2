package com.example;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        // Prompt for username and password (Explicit User Input Flow)
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Login with unfiltered inputs to enhance vulnerability detection
        userService.login(username, password);

        // Unsafe deserialization with explicit file input
        UnsafeSerialization unsafeSerialization = new UnsafeSerialization();
        try {
            System.out.print("Enter file to deserialize: ");
            String file = scanner.nextLine();
            unsafeSerialization.deserializeObject(file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
