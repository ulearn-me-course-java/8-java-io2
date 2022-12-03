package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:
        System.out.println(extractSoundName(new File("task01/src/main/resources/3724.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder p = new ProcessBuilder();
        p.command("C:/Users/Rustam/Desktop/ffmpeg/bin/ffprobe.exe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath())
                .redirectOutput(ProcessBuilder.Redirect.PIPE);
        Process process = p.start();
        ArrayList<String> list = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))){
            reader.lines();
            String s;
            while((s = reader.readLine()) != null){
                list.add(s);
            }
        }
        return getTitle(list);
    }
    static String getTitle(ArrayList<String> attributeList){
        List<String> result = attributeList.stream()
                .filter(lang -> lang.contains("format.tags.title"))
                .collect(Collectors.toList());
        return result.toString().split("\"")[1];
    }
}
