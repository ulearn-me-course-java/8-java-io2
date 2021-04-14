package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //extractSoundName(new File("D:\\IntelliJ IDEA Community Edition 2020.3.2\\8-java-io2\\task01\\src\\main\\resources\\3724.mp3"));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder("ffprobe", "-v", "error", "-of", "flat", "-show_format",
                file.getAbsolutePath());
        Process process = processBuilder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String result;
            while ((result = reader.readLine()) != null){
                if (result.startsWith("format.tags.title="))
                    return result.split("\"")[1];
            }
        }
        return "No answer";
    }
}
