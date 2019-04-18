package com.example.task02;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.nio.file.Files;
import java.util.ArrayList;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> resList = new ArrayList<>();
        Files.walk(rootDir).filter(Files::isRegularFile).forEach(resList::add);
        return resList;
    }
}
