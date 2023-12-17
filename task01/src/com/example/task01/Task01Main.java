package com.example.task01;

import java.io.File;
import java.io.IOException;
import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        System.out.print(extractSoundName(new File("task01/src/main/resources/3724.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " +
                "\"" + file.getAbsolutePath() + "\"");
        pb.directory(new File("C:\\Program Files\\ffmpeg-6.0-essentials_build\\bin"));
        pb.redirectErrorStream(false);
        Process process = pb.start();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.contains("title"))
                    return (line.split("\"")[1]);
            }

        }

            return null;
    }
}
