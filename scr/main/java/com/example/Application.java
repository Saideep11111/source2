package com.example;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Simulate user input to increase detection probability
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        userService.login(username, password);

        // Unsafe deserialization to simulate security alert
        UnsafeSerialization unsafeSerialization = new UnsafeSerialization();
        try {
            unsafeSerialization.deserializeObject("serialized-object.bin");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        scanner.close();
    }
}
