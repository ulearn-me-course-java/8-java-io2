package com.example.task02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> files = new ArrayList<>();

        for (File file : rootDir.toFile().listFiles()) {
            try {
                if (file.isDirectory()) {
                    files.addAll(listFiles(file.toPath()));
                }
                else {
                    files.add(file.toPath());
                }
            }
            catch (Exception e) {
                throw new IOException();
            }
        }
        return files;
    }
}
