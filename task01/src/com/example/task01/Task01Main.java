package com.example.task01;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        // your implementation here
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String[] strings = reader.lines().toArray(String[]::new);
            for (String string : strings) {
                if (string.indexOf("format.tags.title") == 0) {
                    return string.substring(string.lastIndexOf('=') + 2, string.length() - 1);
                }
            }
        }
        throw new RuntimeException("Filename not found.");
    }
}
