package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c",
                "ffprobe -v error -of flat -show_format " + file.getAbsolutePath())
                .directory(new File("D:\\Admin\\Soft\\ffmpeg-n5.0-latest-win64-gpl-5.0\\bin"));

        Process process = processBuilder.start();
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = buffer.readLine();
            while (line != null) {
                if (line.contains("format.tags.title")) {
                    return line.split("\"")[1];
                }
                line = buffer.readLine();
            }
        }
        return null;
    }
}
