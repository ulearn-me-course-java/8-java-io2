package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException
    {
        ProcessBuilder process = new ProcessBuilder
                ("ffprobe -v error -of flat -show_format file.mp3", file.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader
                (new InputStreamReader(process.start().getInputStream())))
        {
            String input;
            while ((input = reader.readLine()) != null)
                if (input.startsWith("format.tags.title"))
                    return input.split("\"")[1];
        }
        return null;
    }
}
