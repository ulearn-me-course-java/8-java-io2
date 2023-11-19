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
        // your implementation here
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " +
                "\"" + file.getAbsolutePath() + "\"")
                .directory(new File("D:/УНИВЕР/2 курс/Java/FFmpeg/ffmpeg-6.0-essentials_build/ffmpeg-6.0-essentials_build/bin"));

        Process process = processBuilder.start();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String str = "";
            while((str = bufferedReader.readLine()) != null){
                if(str.contains("title")) return str.split("\"")[1];
            }
        }
        return null;
    }
}
