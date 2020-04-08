package com.example.task02;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        for (Path path : listFiles(Paths.get("task02/src/main/resources/"))) {
            System.out.println(path.getFileName());
        }
    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> files = new ArrayList<Path>();

        FileVisitor<Path> fileVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                files.add(file);
                return FileVisitResult.CONTINUE;
            }
        };

        Files.walkFileTree(rootDir, fileVisitor);

        return files;
    }
}
