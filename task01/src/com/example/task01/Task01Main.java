package com.example.task01;

import java.io.File;
import java.io.IOException;
import java.nio.*;
import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("/home/wisedog/JohnL.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        Process process = new ProcessBuilder("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath()).start();
        String str;

        try (BufferedReader bf = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        )) {
            str = bf.lines().filter(x ->
                         x.contains("format.tags.title")
            ).findFirst().orElse(null);

            return str.substring(19, str.length() - 1);
        }
    }
}
