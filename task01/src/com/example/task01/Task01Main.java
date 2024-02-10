package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("src/main/resources/3726.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String result = "";
        ProcessBuilder processBuilder = new ProcessBuilder();
        String[] command  = new String[]{
                "cmd.exe",
                "/c",
                "ffprobe -v error -of flat -show_format " + "\"" + file.getAbsolutePath() + "\"",
        };
        processBuilder.command(command)
                .directory(new File("A:/Users/artur/source/repos Java/8-java-io2/task01/src/main/resources"))
                .redirectError(ProcessBuilder.Redirect.INHERIT);
        Process process = processBuilder.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((result = bufferedReader.readLine()) != null) {
            if(result.contains("title")) {
                return result.split("=")[1].replace("\"", "");
            }
        }
        return result;
    }
}