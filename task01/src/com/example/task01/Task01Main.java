package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.function.Predicate;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());
        Process process = processBuilder.start();
        String string = null;

        try (Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()))) {
            while (scanner.hasNext()){
                string = scanner.nextLine();

                if (string.contains("format.tags.title")){
                    string = string.substring(string.indexOf("\"") + 1, string.lastIndexOf("\""));
                    break;
                }
            }
        }
        return string;
    }
}