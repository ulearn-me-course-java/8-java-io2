package com.example.task01;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        String res = null;

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("C:\\FFmpeg\\bin\\ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        Process process = processBuilder.start();

        try (Scanner scan = new Scanner(new InputStreamReader(process.getInputStream()))) {
            while (scan.hasNext()) {
                res = scan.nextLine();

                if (res.contains("format.tags.title")) {
                    res = res.substring(19, res.length() - 1);
                    break;
                }
            }
        }
        return res == null ? "not found info" : res;
    }
}