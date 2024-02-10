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
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "ffprobe -v error -of flat -show_format", file.getAbsolutePath());
        builder.directory(new File("C:\\ffmpeg\\bin"));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(builder.start().getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains("format.tags.title")) {
                    return line.split("\"")[1];
                }
            }
        }
        return null;
    }
}
