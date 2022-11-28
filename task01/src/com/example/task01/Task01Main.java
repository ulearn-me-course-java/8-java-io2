package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        System.out.println(new File("/usr/local/bin/ffmpeg").isFile());
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/usr/local/bin/ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath())
                .redirectOutput(ProcessBuilder.Redirect.PIPE);

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processBuilder.start().getInputStream()))){
            processBuilder.start();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("title"))
                    return getTitle(line);
            }
        } catch (IOException e) {
            System.out.println("exception while reading file: " + e.getMessage());
        }
        return "Name not found";
    }
    private static String getTitle(String line) {
        String titleInBrackets = line.substring(19);
        return titleInBrackets.substring(0, titleInBrackets.length() - 1);
    }
}