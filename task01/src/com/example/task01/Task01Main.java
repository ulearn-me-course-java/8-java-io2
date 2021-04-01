package com.example.task01;

import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());
        Process process = processBuilder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine())!=null){
                if(line.contains("format.tags.title"))
                 return line.split("\"")[1];
            }
        }
        return "Sound haven't name";
    }
}
