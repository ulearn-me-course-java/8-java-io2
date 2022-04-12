package com.example.task01;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        ProcessBuilder process = new ProcessBuilder("cmd.exe","/c","ffprobe -v error -of flat -show_format " + file.getAbsolutePath());
        process.directory(new File("D:\\JavaTasks\\8-java-io2\\ffmpeg\\bin"));

        try(BufferedReader buffer = (new BufferedReader(new InputStreamReader(process.start().getInputStream()))))
        {
            String input = buffer.readLine();
            while(input != null)
            {
                if(input.contains("format.tags.title"))
                    return input.split("\"")[1];

                input = buffer.readLine();
            }
        }

        return "error";
    }
}
