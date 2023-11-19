package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        System.out.println(extractSoundName(new File("task01/src/main/resources/3724.mp3")));
        System.out.println(extractSoundName(new File("task01/src/main/resources/3726.mp3")));

        Tests tests = new Tests();
        tests.test3724();
        tests.test3727();
        tests.test3726();
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " + "\"" + file.getAbsolutePath() + "\"")
                .directory(new File("I:/Work/JavaLabs/ffmpeg/bin"))
                .redirectErrorStream(true);

        Process process = processBuilder.start();
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = buffer.readLine();
            while (line != null) {
                if (line.contains("format.tags.title")) {
                    return line.substring("format.tags.title=\"".length(), line.length() - 1);
                }
                line = buffer.readLine();
            }
        }
        return null;
    }
}
