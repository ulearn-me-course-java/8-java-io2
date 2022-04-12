package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        // your implementation here
        ProcessBuilder processBuilder = new ProcessBuilder("D:\\Загрузки Google\\ffmpeg-2022-04-11-git-d6d46a2c50-essentials_build\\ffmpeg-2022-04-11-git-d6d46a2c50-essentials_build\\bin\\ffprobe.exe",
                "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String trackNameInfo = reader.lines().filter(line -> line .startsWith("format.tags.title")).findFirst().orElse("error");
        return trackNameInfo.split("\"", 3)[1];
    }
}
