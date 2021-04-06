package com.example.task01;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        String soundName;
        ProcessBuilder processBuilder = new ProcessBuilder()
                .command("/Users/igorpetrov/Downloads/ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        Process process = processBuilder.start();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            soundName = reader.lines().filter(a -> a.contains("format.tags.title")).findFirst().get().split("=")[1];
        }

        return soundName.length() > 0 ? soundName.substring(1, soundName.length() - 1) : null;
    }
}
