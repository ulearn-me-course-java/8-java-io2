package com.example.task02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Tests tests = new Tests();
        tests.testRoot();
        tests.testA();
        tests.testE();

        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));
    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        try (Stream<Path> paths = Files.walk(rootDir).filter(Files::isRegularFile)){
            return paths.collect(Collectors.toList());
        }
    }
}




    /*
        List<Path> result = new ArrayList<>();
        if (rootDir.toFile().isDirectory()) {
            for (File item : rootDir.toFile().listFiles()) {
                if (item.isDirectory()) {
                    result.addAll(listFiles(item.toPath()));
                } else {
                    result.add(item.toPath());
                }
            }
        }
        return result;
     */