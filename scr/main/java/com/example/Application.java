package com.example;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        // Prompt user input to increase CodeQL recognition
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        userService.login(username, password);

        // Unsafe deserialization
        UnsafeSerialization unsafeSerialization = new UnsafeSerialization();
        try {
            System.out.print("Enter filename to deserialize: ");
            String file = scanner.nextLine();
            unsafeSerialization.deserializeObject(file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
