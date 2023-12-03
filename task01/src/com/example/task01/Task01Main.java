package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("/task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        // your implementation here
        String nameOfTitle = null;
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe -v error -show_entries format_tags=title 3724.mp3")
                .directory(new File(System.getProperty(file.getCanonicalPath()))).redirectOutput(Redirect.PIPE);
        Process process = processBuilder.start();
        try (BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(process.getInputStream())))   {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                if (line.contains("title"))
                    nameOfTitle = line.split("=")[1];
            }

        }
        return nameOfTitle;
    }
}
