package com.example.task02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));


    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException,NullPointerException {

        List<Path> files = new ArrayList<>();
        try {
            for (File file : rootDir.toFile().listFiles()) {
                try {
                    if (file.isDirectory()) {
                        files.addAll(listFiles(file.toPath()));
                    } else {
                        files.add(file.toPath());
                    }
                } catch (IOException e) {
                    throw new IOException();
                } catch (InterruptedException e) {
                    throw new InterruptedException();
                }
            }
        } catch ( NullPointerException e) {
            throw new NullPointerException();
        }

        return files;
    }
}
