package com.example.task02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        listFiles(Paths.get("/home/wisedog/IdeaProjects/")).forEach(System.out::println);
    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> res = new ArrayList<Path>();
        Files.walk(rootDir).filter(Files::isRegularFile).forEach(res::add);
        return res;
    }
}
