package com.example;

public class VulnerableApp {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.createUser("admin", "password123");
        
        System.out.println("Users:");
        for (String user : userController.getUsers()) {
            System.out.println(user);
        }
    }
}
