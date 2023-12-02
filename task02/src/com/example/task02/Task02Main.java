package com.example.task02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
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
        List<Path> result = new ArrayList<>();
        if (rootDir.toFile().isDirectory()) {
            File[] files = rootDir.toFile().listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    result.addAll(listFiles(file.toPath()));
                } else {
                    result.add(file.toPath());
                }
            }
        }
        return result;
    }
}
