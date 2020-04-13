package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String nameOfSound;
        ProcessBuilder processBuilder = new ProcessBuilder()
                .command("ffprobe",
                        "-v", "error", "-of", "flat","-show_format", file.getAbsolutePath());
        Process process = processBuilder.start();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            nameOfSound = getTitle(bufferedReader);
        }
        return nameOfSound;
    }

    private static String getTitle(BufferedReader bufferedReader) {
        String titleName = bufferedReader.lines().filter(line -> line.contains("title")).findFirst().get();
        int start = titleName.indexOf('\"') + 1;
        int end = titleName.indexOf('\"', start);
        return titleName.substring(start, end);
    }
}


