package com.example.task01;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String result = "";

        ProcessBuilder processBulder = new ProcessBuilder();
        processBulder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        Process process = processBulder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        try {
            String[] lines = reader.lines().toArray(String[]::new);
            for (String line : lines)
                if (line.indexOf("format.tags.title") == 0) {
                    result = line.substring(19, line.length() - 1);
                    break;
                }
        } catch (Exception e) {
            throw new RuntimeException("Имя файла не найдено");
        } finally {
            reader.close();
        }

        return result;
    }
}
