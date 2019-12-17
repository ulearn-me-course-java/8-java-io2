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
        String name = null;
        StringBuilder call = new StringBuilder("ffprobe -v error -of flat -show_format ");
        call.append(file.getAbsolutePath());
        Process proc = Runtime.getRuntime().exec(call.toString());
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            String splitted[] = s.split("=");
            if (splitted[0].equals("format.tags.title") && splitted.length > 1) {
                name = splitted[1];
            }
        }
        return name;
    }
}
