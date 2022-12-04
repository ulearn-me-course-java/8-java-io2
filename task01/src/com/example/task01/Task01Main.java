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
        String command = "ffprobe -v error -of flat -show_format " + file.getAbsoluteFile();

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd", "/c", command);

        Process p = processBuilder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String result = "";
        String line = "";

        while (true) {
            line = r.readLine();
            if (line == null) break;

            String[] parts = line.split("=");
            if (parts[0].equals("format.tags.title")) {
                result = parts[1].substring(1, parts[1].length()-1);
            }
        }
        return result;
    }
}
