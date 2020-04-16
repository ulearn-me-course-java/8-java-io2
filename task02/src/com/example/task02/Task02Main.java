package com.example.task02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Реализуйте рекурсивный обход заданного каталога, используя java.nio
        //
        //Результатом работы метода listFiles должен быть список все файлов в каталоге rootDir.

        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {

        ArrayList<Path> filesBypass = new ArrayList<>();

            Files.walk(rootDir)
                    .filter(path -> Files.isRegularFile(path))
                    .forEach(path -> filesBypass.add(path));
        return filesBypass;
    }
}
