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
//        String path = file.getAbsolutePath()
//                .substring(0, file.getAbsolutePath()
//                .lastIndexOf(File.separator));//for use getName()
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder
                .command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());
        Process process = processBuilder.start();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String[] input = reader.lines().toArray(String[]::new);
            for (String str : input)
                if (str.indexOf("format.tags.title") == 0)
                    return str.substring(str.lastIndexOf('=') + 2, str.length() - 1);
        }
        throw new RuntimeException("Error inputs.");
    }
}
