package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    private static final String statisticInfo = "ffprobe -v error -of flat -show_format";
    private static final String tagTitle = "format.tags.title=";

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        return extractSomeFeature(file, statisticInfo, tagTitle);
    }

    public static String extractSomeFeature(File file, String query, String feature) throws IOException, InterruptedException {
        if (file == null || query == null || feature == null) throw new NullPointerException();
        Process process = new ProcessBuilder()
                .command((query + " " + file.getName()).split(" "))
                .directory(new File(file.getParent()))
                .start();
        String result;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        )) {
            result = reader.lines().
                    filter(x -> x.startsWith(feature))
                    .findAny().orElse(null);
        }
        int successValue = process.waitFor();
        if (successValue != 0)
            return "Process terminated with value: " + successValue;
        return result == null ? "not found info" : result.substring(feature.length() + 1, result.length() - 1);
    }
}
