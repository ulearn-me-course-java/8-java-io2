package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBulder = new ProcessBuilder();
        processBulder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath())
                .redirectOutput(Redirect.PIPE)
                .redirectError(Redirect.INHERIT);

        Process process = processBulder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String[] lines = reader.lines().toArray(String[]::new);
            for (String line : lines)
                if (line.indexOf("format.tags.title") == 0)
                    return line.substring(19, line.length() - 1);
        }
        throw new RuntimeException("Имя файла не найдено");
    }
}
