package com.example.task02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Task02Main {
    public static void main(String[] args) throws IOException{
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));


    }

    public static List<Path> listFiles(Path rootDir) throws IOException {
        List<Path> pathList = Files.walk(rootDir).collect(Collectors.toList());

        List<Path> result = new ArrayList<>();
        for(Path path : pathList){
            if(path.toFile().isFile()){
                result.add(path);
            }
        }

        return result;
    }
}
