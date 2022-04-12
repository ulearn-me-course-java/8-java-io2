package com.example.task03;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class Task03Main {
    public static void main(String[] args) {

        /*
        System.out.println(deserialize(new FileInputStream("task03/src/main/resources/example1.bin")));
        */

    }

    public static SampleData deserialize(InputStream inputStream) throws IOException, ClassNotFoundException{
        SampleData deserializedData;
        try(ObjectInputStream ois = new ObjectInputStream(inputStream)){
            deserializedData = (SampleData) ois.readObject();
        }
        return deserializedData;
    }
}
