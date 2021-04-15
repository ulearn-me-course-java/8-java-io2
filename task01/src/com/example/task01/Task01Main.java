package com.example.task01;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3726.mp3")));
    }

    public static String extractSoundName(File file) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe",
                    "-v", "error",
                    "-of", "flat",
                    "-show_format",
                    file.getAbsolutePath());

        Process process = processBuilder.start();

        Scanner scanner = new Scanner(process.getInputStream());

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("format.tags.title")) {
                return line.substring(line.indexOf('"') + 1, line.lastIndexOf('"'));
            }
        }

        return null;
    }
}
