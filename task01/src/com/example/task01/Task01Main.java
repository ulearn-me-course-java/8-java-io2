package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        return extractSomeFeature(file, "ffprobe -v error -of flat -show_format", "format.tags.title=");
    }

    public static String extractSomeFeature(File file, String query, String feature) throws IOException, InterruptedException {
        if (file == null || query == null || feature == null){
            throw new NullPointerException();
        }
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command((query + " " + file.getName()).split(" "))
                .directory(new File(file.getParent()))
                .redirectInput(ProcessBuilder.Redirect.from(new File("/dev/null")))
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.INHERIT); ///whatahul
        Process process = processBuilder.start();String rezult;
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        )){
            rezult = reader.lines().
                    filter(x -> x.startsWith(feature))
                    .findAny().orElse("");
        }
        int exitVakue = process.waitFor();
        return rezult.substring(feature.length() + 1, rezult.length() - 1);
    }
}
