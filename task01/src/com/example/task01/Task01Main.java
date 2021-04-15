package com.example.task01;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("C:\\Users\\пк\\IdeaProjects\\8-java-io2\\task01\\src\\main\\resources\\3724.mp3");
        System.out.println(extractSoundName(file));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder ypb = new ProcessBuilder("D:\\Files\\Helpers\\ffmpeg-n4.4-4-gacb339bb88-win64-gpl-4.4\\bin\\ffprobe.exe",
            "-v",
            "error",
            "-of",
            "flat",
            "-show_format",
            file.getAbsolutePath()
        );

        Process yp = ypb.start();

        try (BufferedReader yBr = new BufferedReader(new InputStreamReader(yp.getInputStream()))) {
            String line;

            while ((line = yBr.readLine()) != null) {
                if (line.contains("title")) {
                    line = line.split("=")[1].replaceAll("\"","");
                    return line;
                }
            }
        }

        return "Результат не найден!";
    }
}
