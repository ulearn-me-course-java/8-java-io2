package com.example.task01;

import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        // your implementation here
        /*ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", String.format("ffprobe -v error -of flat -show_format \"{0}\"", file.getAbsolutePath()))
                .directory(new File("C:\\ffmpeg\\bin"));

        Process process = processBuilder.start();
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = buffer.readLine();
            while (line != null) {
                if (line.contains("title")) {
                    return line.split("=")[1];
                }
                line = buffer.readLine();
            }
        }*/
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " + "\"" + file.getAbsolutePath() + "\"")
                .directory(new File("C:\\ffmpeg\\bin"))
                .redirectErrorStream(true);
        Process process = processBuilder.start();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains("title")) {
                    return line.split("=")[1].replace("\"", "");
                }
            }
        }
        return null;
    }
}
