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

    public static final String _ffmpegDir = "C:/Users/danbo/Downloads/ffmpeg";

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "ffprobe -v error -of flat -show_format", file.getAbsolutePath());
        builder.directory(new File(_ffmpegDir));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(builder.start().getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains("format.tags.title")) {
                    return line.substring(line.indexOf('=') + 1).replace("\"", "");
                }
            }
        }
        return null;
    }
}
