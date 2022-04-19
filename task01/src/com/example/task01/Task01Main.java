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
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "ffprobe -v error -of flat -show_format", file.getAbsolutePath())
                .directory(new File("H:\\Program Files\\ffmpeg\\bin"));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(processBuilder.start().getInputStream()))) {
            String line;
            do {
                line = reader.readLine();
                if (line.contains("format.tags.title")) {
                    return line.split("\"")[1];
                }
            }
            while (!line.isEmpty());
        }
        return null;
    }
}
