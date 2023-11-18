package com.example.task01;

import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("C:\\Users\\pepenka\\Desktop\\ffmpeg-6.1-essentials_build\\bin\\ffprobe.exe",
                "-v", "error", "-of", "flat", "-show_format", file.getCanonicalPath());
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if(line.split("=")[0].equals("format.tags.title")){
                return line.split("=")[1].replace("\"", "");
            }
        }

        return "";
    }
}

