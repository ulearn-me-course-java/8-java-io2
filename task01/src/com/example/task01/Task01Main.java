package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("task01/src/main/resources/3724.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String soundName;

        ProcessBuilder processBuilder = new ProcessBuilder() //использование коммандной строки
                .command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath()); //getabsolutepath - путь с С:\
        Process process = processBuilder.start(); //запуск процесса;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        soundName = getTitle(bufferedReader);

        return soundName;
    }

    private static String getTitle(BufferedReader bufferedReader) {
        String titleName = bufferedReader.lines()
                .filter(line -> line.contains("title"))
                .findFirst()
                .get();
        int start = titleName.indexOf('\"') + 1;
        int end = titleName.indexOf('\"', start);
        return titleName.substring(start, end);
    }
}