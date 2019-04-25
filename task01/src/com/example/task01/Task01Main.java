package com.example.task01;

import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String result = null;

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        Process process = processBuilder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            for(String str : reader.lines().toArray(String[]::new)) {
                if(str.startsWith("format.tags.title")) {
                    result = str.substring(str.indexOf('=')+2, str.length() - 1);
                }
            }
        }
        return result;
    }
}
