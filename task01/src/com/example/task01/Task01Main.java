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
        Tests tests = new Tests();
        tests.test3724();
        tests.test3726();
        tests.test3727();
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " + "\"" + file.getAbsolutePath() + "\"");
        processBuilder.directory(new File("task01/src/com/example/task01"));
        processBuilder.redirectInput(file);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        Process process = processBuilder.start();
        String res = "";
        try (BufferedReader reader= new BufferedReader(new InputStreamReader(process.getInputStream()))){
            for (String line:
                    reader.lines().collect(Collectors.toList())) {
                if (line.contains("format.tags.title")){
                    res = line.split("=")[1].replace("\"","");
                }
            }
        }
        return res;
    }
}
