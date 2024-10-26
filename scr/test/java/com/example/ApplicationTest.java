package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {
    @Test
    public void testLogin() {
        UserService userService = new UserService();
        
        // Hardcoded test credentials (Vulnerability #8)
        assertTrue(userService.login("admin", "password123"));
    }
    
    // Example of a potential resource leak (Vulnerability #9)
    @Test
    public void testResourceLeak() {
        UserService userService = new UserService();
        for (int i = 0; i < 10; i++) {
            userService.login("user" + i, "password" + i);
        }
    }
    
    // Exposure of sensitive data in log (Vulnerability #10)
    @Test
    public void testSensitiveDataExposure() {
        UserService userService = new UserService();
        System.out.println("Testing credit card info exposure: " + userService.getCreditCardInfo("user1"));
    }
}
