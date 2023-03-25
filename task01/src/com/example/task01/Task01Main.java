package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3726.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/bin/bash", "-c", "ffprobe -v error -of flat -show_format " + "\""+ file.getAbsolutePath() + "\"")
                .directory(new File("/opt/homebrew/Cellar/ffmpeg/5.1.2_6/bin"))
                .redirectInput(ProcessBuilder.Redirect.from(new File("/dev/null")))
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.INHERIT);

        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader( //try гарантирует завершение и вызов метода .close()
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains("title")) {
                    return line.split("=")[1].replace("\"", "");
                }
            }
        }

        int exitValue = process.waitFor();
        if (exitValue != 0) {
            System.err.println("Subprocess terminated abnormally");
        }

        return null;
    }
}
