package com.example.task02;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));


    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> files = new ArrayList<>();
        /*
        Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                files.add(file);
                return FileVisitResult.CONTINUE;
            }
        });
        */

        files = Files.walk(rootDir)
                .filter(path -> !Files.isDirectory(path))
                .collect(Collectors.toList());

        return files;
    }
}
