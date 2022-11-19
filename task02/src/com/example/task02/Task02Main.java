package com.example.task02;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));
        */

    }


    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {

        List<Path> files = new ArrayList<>();

        Files.list(rootDir).forEach(path -> {
            try {
                if (Files.isDirectory(path)) {
                    files.addAll(listFiles(Paths.get(path.toString())));
                }
                else {
                    files.add(path);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return files;
    }
}
