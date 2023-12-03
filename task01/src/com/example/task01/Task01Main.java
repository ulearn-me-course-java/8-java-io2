package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException
    {
        // your implementation here
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("C:/ffmpeg3/bin/ffprobe.exe", "-v", "error", "-of", "flat", "-show_format", file.getCanonicalPath());
        Process p = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream())))
        {
            String string = "";
            while (!string.contains("format.tags.title"))
            {
                string = reader.readLine();
            }
            return string.substring(19).replace("\"","");
        }
    }
}
