package com.example.task01;

import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
       //System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException {
        String songName;

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        Process process = processBuilder.start();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            songName = reader.lines()
                    .filter(line -> line.contains("title"))
                    .findFirst()
                    .get();
        }

        int start = songName.indexOf('\"') + 1;
        int end = songName.indexOf('\"', start);
        return songName.substring(start, end);
    }
}
