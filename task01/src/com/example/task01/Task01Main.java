package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Task01Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\User\\Downloads\\Асия-Останься.mp3");
        System.out.println(extractSoundName(file));
    }

    public static String extractSoundName(File file) {
        List<String> command = Arrays.asList("ffprobe", "-v", "error",
                "-of", "flat=s=_", "-show_format", file.getAbsolutePath());

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = processOutput.readLine()) != null) {
                if (line.contains("title")) {
                    return line.substring(line.indexOf('"')).replace("\"", "");
                }
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Ошибка выполнения команды: " + e.getMessage());
        }
        return null;
    }
}
