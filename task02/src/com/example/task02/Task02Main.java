package com.example.task02;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        List<Path> list = listFiles(Paths.get("task02/src/main/resources/"));
        for(Path path : list){
            System.out.println(path.toString());
        }

    }

    public static List<Path> listFiles(Path rootDir) throws IOException {
        List<Path> listPath = new ArrayList<Path>();
       /* Files.walk(Paths.get(rootDir.toString()))
                .filter(Files::isRegularFile)
                .forEach(System.out::println);*/
        /*try (Stream<Path> walk = Files.walk(rootDir)) {
            listPath = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }*/
        Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes attr) throws IOException{
                listPath.add(file);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException ex) {
                return FileVisitResult.CONTINUE;
            }
        });
        return listPath;
    }
}
