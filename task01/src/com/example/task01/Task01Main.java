package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException{
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException{
        ProcessBuilder processBuilder = new ProcessBuilder(
                "C:\\ffmpeg\\ffprobe.exe",
                "-v", "error", "-of", "flat", "-show_format", file.getCanonicalPath()
        );
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String line;
            while((line = reader.readLine()) != null){
                if(line.startsWith("format.tags.title")){
                    return line.split("=")[1].replace("\"", "");
                }
            }
        }
        return null;
    }
}
