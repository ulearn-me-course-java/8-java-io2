package com.example.task02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));
        */

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        // your implementation here
        try (Stream<Path> walk = Files.walk(Paths.get(rootDir.toString()))) {

            List<Path> result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
