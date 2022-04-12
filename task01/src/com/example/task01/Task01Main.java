package com.example.task01;

import java.io.File;
import java.io.IOException;
import java.io.*;

public class Task01Main {
    public static void main(String[] args) {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        //System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe.exe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath())
                .directory(new File("C:\\Users\\pmaxq\\OneDrive\\Desktop"))
                .redirectError(ProcessBuilder.Redirect.INHERIT);

        Process process = processBuilder.start();

        InputStream stream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String currentData;
        while( (currentData = bufferedReader.readLine()) != null){
            if(currentData.contains("format.tags.title")){
                return currentData.replace("format.tags.title=", "").replace("\"", "");
            }
        }

        bufferedReader.close();
        return "Unknown artist";
    }
}
