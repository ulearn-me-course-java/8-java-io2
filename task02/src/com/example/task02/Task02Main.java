package com.example.task02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> yList = new ArrayList<Path>();

        Files.walk(Paths.get(String.valueOf(rootDir)))
                .filter(Files::isRegularFile)
                .forEach(yList::add);
        return yList;
    }
}
