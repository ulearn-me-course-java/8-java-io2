package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String path = String.format("\"%s\"", file.getAbsolutePath());
        ProcessBuilder processBuilder = new ProcessBuilder("ffprobe", "-v", "error", "-of", "flat", "-show_format", path);
        Process process = processBuilder.start();

        String soundName;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            soundName = reader.lines()
                              .filter(p -> p.contains("format.tags.title"))
                              .flatMap(p -> Arrays.stream(p.split("\"")).skip(1))
                              .collect(Collectors.joining());
        }

        return soundName;
    }
}
