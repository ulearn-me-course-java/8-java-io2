package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("C:\\Users\\Дмитрий\\Desktop\\Queen_-_Another_One_Bites_The_Dust.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String[] strings = reader.lines().toArray(String[]::new);
            for (String string : strings) {
                if (string.indexOf("format.tags.title") == 0) {
                    return string.substring(19, string.length() - 1);
                }
            }
        }
        throw new RuntimeException("Filename not found.");
    }
}
