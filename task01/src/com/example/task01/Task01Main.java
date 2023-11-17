package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException{
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder
                .command("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " + "\"" + file.getAbsolutePath() + "\"")
                .directory(new File("C:\\Users\\79681\\Desktop\\laba8progs\\"))
                .redirectErrorStream(false); //здесь записали false, ибо не перенаправляем ошибки в наш stderr
        Process process = processBuilder.start();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String buffer = "";
            while ((buffer = reader.readLine()) != null)
                if(buffer.contains("title"))
                    return buffer.split("\"")[1];

        }

        return null;
    }
}
