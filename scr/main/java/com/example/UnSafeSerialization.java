package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class UnsafeSerialization {
    // Insecure deserialization vulnerability (Vulnerability #7)
    public void deserializeObject(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            System.out.println("Deserialized object: " + obj);
        }
    }
}
