package com.example.task03;

import java.io.*;

public class Task03Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(deserialize(new FileInputStream("task03/src/main/resources/example1.bin")));
        */

    }

    public static SampleData deserialize(InputStream inputStream) throws IOException, ClassNotFoundException {
        SampleData result;
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            result = (SampleData) ois.readObject();
        }
        return result;
    }
}
