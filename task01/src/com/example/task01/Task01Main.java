package com.example.task01;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
            System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
            System.out.println(extractSoundName(new File("task01/src/main/resources/3724.mp3")));
            System.out.println(extractSoundName(new File("task01/src/main/resources/3726.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        ProcessBuilder.command("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " + "\"" + file.getAbsolutePath() + "\"")
                .directory(new File("D:\\ffm\\bin"))
                .redirectErrorStream(true);
        Process process = processBuilder.start();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains("title")) {
                    return line.split("=")[1].replace("\"", "");
                }
            }
        }
        return null;
    }
}
