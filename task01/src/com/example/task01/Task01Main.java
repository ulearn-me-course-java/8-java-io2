package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {

       System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder process = new ProcessBuilder("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " + file.getAbsolutePath());
        process.directory(new File("C:\\Users\\Lenovo\\IdeaProjects\\8-java-io2\\task01\\src\\com\\example\\task01"))
                .redirectOutput(ProcessBuilder.Redirect.PIPE);
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(process.start().getInputStream()))) {
            String txt = buffer.readLine();
            while (txt != null){
                if (txt.contains("format.tags.title"))
                    return txt.split("\"")[1];
                txt = buffer.readLine();
            }
        }
        return null;
    }

}
