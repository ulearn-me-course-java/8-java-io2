package com.example.task02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task02Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));
    }

    public static List<Path> listFiles(Path rootDir) {
        List<Path> result = new ArrayList<>();
        for (File file : Objects.requireNonNull(rootDir.toFile().listFiles())) {
            if(file.isDirectory()) result.addAll(listFiles(file.toPath()));
            else result.add(file.toPath());
        }
        return result;
    }

}
