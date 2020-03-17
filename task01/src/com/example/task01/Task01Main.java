package com.example.task01;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("/Users/grin/IdeaProjects/8-java-io2/task01/ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath())
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
