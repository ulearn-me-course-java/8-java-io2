package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01\\src\\main\\resources\\3724")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        String ffPath = "C:\\Users\\lilit\\OneDrive\\Рабочий стол\\ffmpeg-20190417-8a3ed5a-win64-static\\bin\\ffprobe.exe";
        ProcessBuilder pB = new ProcessBuilder(ffPath, "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        Process process = pB.start();

        try(BufferedReader bR = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String res = bR.lines()
                           .filter(s -> s.startsWith("format.tags.title="))
                           .findAny()
                           .orElse(null);
            return res.substring(19, res.length() - 1);
        }
    }
}
