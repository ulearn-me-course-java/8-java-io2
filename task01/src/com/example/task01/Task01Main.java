package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3724.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("C:\\Users\\Админ\\Desktop\\ffmpeg-20190421-6e0488c-win64-static\\bin\\ffprobe.exe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());
        Process process = processBuilder.start();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String[] lines=reader.lines().toArray(String[]::new);
            for (String el:lines
                 ) {
                int index= el.indexOf("=");
                if((el.substring(0,index)).equals("format.tags.title")) {
                     return el.substring(index+2, el.lastIndexOf("\""));
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "";

    }
}
