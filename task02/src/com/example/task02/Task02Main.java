package com.example.task02;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Task02Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Path path = Paths.get("task02/src/main/resources/");
    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException
    {
        List<Path> paths = new ArrayList<>();
        addFiles(paths, rootDir);
        return paths;
    }

    private static void addFiles(List<Path> paths, Path dir)
    {
        File[] files = dir.toFile().listFiles();
        for (File f : files)
            if (f.listFiles() == null)
                paths.add(f.toPath());
            else
                addFiles(paths, f.toPath());
    }
}
