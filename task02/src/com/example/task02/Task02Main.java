package com.example.task02;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Path> paths = listFiles(Paths.get("task02/src/main/resources"));
        for (Path a:paths) {
            System.out.println(a);
        }
    }
    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> paths = new ArrayList<>();
        File file = rootDir.toFile();
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                paths.addAll(listFiles(Paths.get(f.getPath())));
            }else {
                paths.add(Paths.get(f.getPath()));
            }
        }
        return paths;
    }
}