package com.example.task01;

import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:
        System.out.println(extractSoundName(new File("task01/src/main/resources/3726.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        File ffprobe = new File("task01/src/com/example/task01/ffprobe.exe");
        processBuilder.command(ffprobe.getAbsolutePath(), "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());
        Process process = processBuilder.start();
        InputStream in = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String result = null;
        String line;
        while ((line = reader.readLine()) != null){
            if (line.contains("format.tags.title")){
                result = line.substring("format.tags.title=\"".length(),line.length() - 1);
                break;
            }
        }

        reader.close();

        return result;
    }
}
