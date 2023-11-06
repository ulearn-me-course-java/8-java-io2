package com.example.task02;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> result = new ArrayList<Path>();
        if(rootDir.toFile().isDirectory()) {
            for(File file: rootDir.toFile().listFiles()) {
                if(file.isDirectory()){
                    result.addAll(listFiles(file.toPath()));
                }
                else {
                    result.add(file.toPath());
                }
            }
        }
        return result;
    }
}
