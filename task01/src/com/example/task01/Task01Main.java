package com.example.task01;

import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        System.out.println(extractSoundName(new File("task01/src/main/resources/3724.mp3")));
        System.out.println(extractSoundName(new File("task01/src/main/resources/3726.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe","/c","ffprobe -v error -of flat -show_format " + file.getAbsolutePath())
                .directory(new File("E:\\JavaProjects\\8-java-io2\\ffmpeg-5.0.1-full_build\\bin"));

        Process process = processBuilder.start();
        try(BufferedReader buffer = (new BufferedReader(new InputStreamReader(process.getInputStream()))))
        {
            String line = buffer.readLine();
            while(line != null)
            {
                //System.out.println(line);
                if(line.contains("title")){
                    return line.split("\"")[1];
                }
                line = buffer.readLine();
            }
        }

        return null;
    }
}
