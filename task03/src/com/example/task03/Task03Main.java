package com.example.task03;

import java.io.*;

public class Task03Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(deserialize(new FileInputStream("task03/src/main/resources/example1.bin")));
    }

    public static SampleData deserialize(InputStream inputStream) throws IOException, ClassNotFoundException {
        SampleData deserializedSampleData;
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            deserializedSampleData = (SampleData) ois.readObject();
        }
        return deserializedSampleData;
    }
}
