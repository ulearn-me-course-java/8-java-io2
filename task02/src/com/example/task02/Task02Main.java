package com.example.task02;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
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
        List<Path> listOfFiles = new ArrayList<>();
        Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc == null) {
                    for (File el : dir.toFile().listFiles()
                    ) {
                        if (Files.isRegularFile(el.toPath()))
                            listOfFiles.add(el.toPath());
                    }

                    return FileVisitResult.CONTINUE;
                } else
                    throw exc;
            }
        });

        return listOfFiles;
    }
}
