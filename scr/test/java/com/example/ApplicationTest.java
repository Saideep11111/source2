package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationTest {
    @Test
    public void testLogin() {
        UserService userService = new UserService();
        assertTrue(userService.login("admin", "password123")); // Hardcoded credentials
    }
    
    // Resource leak in a loop for CodeQL to detect
    @Test
    public void testResourceLeak() {
        UserService userService = new UserService();
        for (int i = 0; i < 10; i++) {
            userService.login("user" + i, "password" + i);
        }
    }
    
    // Expose sensitive data through logging
    @Test
    public void testSensitiveDataExposure() {
        UserService userService = new UserService();
        System.out.println("Credit Card Info: " + userService.getCreditCardInfo("user1"));
    }
}
