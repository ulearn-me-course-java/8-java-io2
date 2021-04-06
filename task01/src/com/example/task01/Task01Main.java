package com.example.task01;

import java.io.*;
import java.util.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        String soundName;
        ProcessBuilder processBuilder = new ProcessBuilder()
                .command("cmd.exe","/c","ffprobe","-v","error","-of","flat","-show_format", file.getAbsolutePath())
                .redirectOutput(ProcessBuilder.Redirect.PIPE);
        Process process = processBuilder.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String result = bufferedReader.readLine();
        while (result != null) {
            if (result.contains("format.tags.title")) {
                result = result.substring(19,result.length()-1);
                break;
            }
            result = bufferedReader.readLine();
        }

        return result;
    }
}
