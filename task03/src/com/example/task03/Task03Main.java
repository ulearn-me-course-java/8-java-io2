package com.example.task03;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Date;

public class Task03Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SampleData ysd = new SampleData("yD",1,new Date());
        System.out.println(ysd.name);
    }

    public static SampleData deserialize(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (SampleData) objectInputStream.readObject();
    }
}
