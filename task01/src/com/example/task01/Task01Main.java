package com.example.task01;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Task01Main {
    public static void main(String[] args) throws IOException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException {
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath())
                .redirectOutput(ProcessBuilder.Redirect.PIPE);

        Process prc = pb.start();
        Scanner sc = new Scanner(prc.getInputStream()).useDelimiter("format\\.");

        while (sc.hasNext()){
            String info = sc.next();
            if(info.contains("title"))
                return info.replaceAll("tags.title=", "")
                        .replace("\"", "")
                        .replace("\n", "");
        }
        throw new IllegalArgumentException();
        //Я знаю, что это очень плохо, это надо сделать по-другому
        //но мне лень (((
    }
}
