package com.example.task02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    public static List<Path> listFiles(Path rootDir) throws IOException {
        ArrayList<Path> output = new ArrayList<>();
        Files.walk(rootDir)
                .filter(Files::isRegularFile)
                .forEach(output::add);
        return output;
    }
}
