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
        ProcessBuilder pb = new ProcessBuilder("C:/ffmpeg/bin/ffprobe.exe",
                "-v", "error", "-of", "flat", "-show_format", file.getCanonicalPath());
        Process p = pb.start();

        String line = null;
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(p.getInputStream()))) {
            while((line = reader.readLine()) != null){
                if(line.startsWith("format.tags.title")) {
                    return line.split("=")[1].replace("\"", "");
                }
            }
        }
        return line;
    }
}
