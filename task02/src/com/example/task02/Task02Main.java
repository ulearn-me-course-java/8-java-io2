package com.example.task02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> paths = new ArrayList<Path>();
        for (File file : Objects.requireNonNull(rootDir.toFile().listFiles())) {
            if (file.isDirectory()){
                paths.addAll(listFiles(file.toPath()));
            }
            else{
                paths.add(file.toPath());
            }
        }
        return paths;
    }
}
