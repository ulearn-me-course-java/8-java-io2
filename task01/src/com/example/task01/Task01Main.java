package com.example.task01;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder
                .command("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " + file.getAbsolutePath())
                .directory(new File("C:\\ffmpeg\\bin"))
                .redirectErrorStream(true);

        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String str = bufferedReader.readLine();
            while (str != null) {
                if (str.contains("format.tags.title")) {
                    return str.split("=")[1].replace("\"", "");
                }
                str = bufferedReader.readLine();
            }
        }

        return null;
    }
}
