package com.example.task02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));
        */

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        if (rootDir == null || !rootDir.toFile().isDirectory()) {
            throw new IllegalArgumentException();
        }
        List<Path> files = new LinkedList<>();
        File f = rootDir.toFile();
        for (File lFile : f.listFiles()) {
            if (lFile.isDirectory() && !lFile.toPath().equals(rootDir)) {
                files.addAll(listFiles(lFile.toPath()));
            }
            files.add(lFile.toPath());
        }
        return files;
    }
}
