package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }
    static String ffprobe = "D:\\Programs\\ffmpeg-N-101864-g0f6a3405e8-win64-gpl-shared\\bin\\ffprobe.exe";
    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(ffprobe, "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath())
                .redirectOutput(ProcessBuilder.Redirect.PIPE);
        Process process = processBuilder.start();

        String title = "format.tags.title";
        String soundName = "";
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))){
            soundName = reader.lines()
                              .filter(p -> p.contains(title))
                              .findFirst()
                              .map(x -> x.substring(title.length() + 2, x.length() - 1))
                              .get();
        }
        return soundName;
    }
}
