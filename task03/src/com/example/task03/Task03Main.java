package com.example.task03;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task03Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(deserialize(Files.newInputStream(Paths.get("task03/src/main/resources/example1.bin"))));
        System.out.println(deserialize(Files.newInputStream(Paths.get("task03/src/main/resources/example2.bin"))));

        Tests tests = new Tests();
        tests.test();
    }

    public static SampleData deserialize(InputStream inputStream) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return (SampleData) objectInputStream.readObject();
        }
    }
}
