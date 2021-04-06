package com.example.task02;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        // your implementation here
        List<Path> listFiles = new ArrayList<>();
        return findFiles(rootDir, listFiles);
    }

    public static List<Path> findFiles(Path rootDir, List<Path> listF) throws IOException{
        if (Files.exists(rootDir) && Files.isDirectory(rootDir)) {
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(rootDir)) {
                for (Path child : ds)
                    if (child.toFile().isDirectory())
                        findFiles(child, listF);
                    else
                        listF.add(child);
            }
        }
        return listF;
    }
}
