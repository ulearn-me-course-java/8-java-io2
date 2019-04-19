package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3724")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        ProcessBuilder pB = new ProcessBuilder("ffprobe -v error -of flat -show_format file.mp3", file.getAbsolutePath());

        Process process = pB.start();

        try(BufferedReader bR = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String res = bR.lines()
                           .filter(s -> s.startsWith("format.tags.title="))
                           .findAny()
                           .orElse(null);
            return res.substring(19, res.length() - 1);
        }
    }
}
