package com.example.task02;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> result = new ArrayList<>();

        Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                result.add(file);
                return FileVisitResult.CONTINUE;
            }
        });

        return result;
    }
}
