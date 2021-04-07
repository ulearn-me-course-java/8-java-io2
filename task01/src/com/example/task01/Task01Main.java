package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static String extractSoundName(File file) throws IOException {

        ProcessBuilder processBuilder = new ProcessBuilder()
                .command("ffprobe", "-v", "error", "-of", "flat", "-show_format",
                        file.getAbsolutePath());
        Process process = processBuilder.start();

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line = " ";
            while (line != null) {
                if (line.contains("format.tags.title"))
                    return line.split("\"")[1];

                line = bufferedReader.readLine();
            }

            throw new RuntimeException("Sound name not found");
        }
    }
}
