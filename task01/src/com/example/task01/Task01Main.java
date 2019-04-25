package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Predicate;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String answer;
        ProcessBuilder recognizer = new ProcessBuilder();
        recognizer.command("ffprobe","-v","error", "-of", "flat", "-show_format", file.getPath())
                .redirectOutput(ProcessBuilder.Redirect.PIPE);
        Predicate<String> predicate = (s) -> s.indexOf("format.tags.title") == 0;
        Process process = recognizer.start();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            answer = (String) reader.lines().filter(predicate).toArray()[0];
            answer = answer.substring(answer.indexOf("\"") + 1, answer.lastIndexOf("\""));
        }
        return answer;
    }
}
