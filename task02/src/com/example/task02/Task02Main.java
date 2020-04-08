package com.example.task02;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Task02Main {
    public static void main(String[] args) throws IOException{
         System.out.println(listFiles(Paths.get("task02/src/main/resources/")));
    }

    public static List<Path> listFiles(Path rootDir) throws IOException{
        List<Path> result = new ArrayList<>();
        Files.walk(rootDir)
                .filter(path->Files.isRegularFile(path))
                .forEach(result::add);
        return result;
    }
}