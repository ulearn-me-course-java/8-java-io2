package com.example.task02;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        return Files.walk(rootDir).filter(x -> Files.isRegularFile(x)).collect(Collectors.toList());
    }
}
