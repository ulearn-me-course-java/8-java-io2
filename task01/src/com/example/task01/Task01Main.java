package com.example.task01;
import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException {
        String title = "format.tags.title=";
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());
                //.redirectOutput(ProcessBuilder.Redirect.PIPE      ?? Зачем этот PIPE, если и без него работает? ??
                //.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            return reader.lines()
                    .filter(y -> y.startsWith(title))
                    .map(x -> x.substring(title.length() + 1, x.length() - 1))
                    .findFirst().get();
        }
    }
}