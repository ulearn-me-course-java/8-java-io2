package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.System.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " + "\"" + file.getAbsolutePath() + "\"")
                .directory(new File("C:\\Program Files\\ffmpeg\\bin"))
                .redirectInput(file)
                .redirectErrorStream(true);

        Process pr = pb.start();

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(pr.getInputStream()))) {
            String str;
            while ((str = reader.readLine()) != null) {
                if(str.contains("title"))
                    return str.split("=")[1].replace("\"", "");
            }
        }

        return null;
    }
}
