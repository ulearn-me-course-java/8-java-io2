package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        System.out.println(extractSoundName(new File("src/main/resources/3724.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd", "/c", "ffprobe -v error -of flat -show_format", file.getAbsolutePath())
                    .directory(new File("D:\\Downloads\\ffmpeg-N-101864-g0f6a3405e8-win64-gpl\\ffmpeg-N-101864-g0f6a3405e8-win64-gpl\\bin"));
        Process process = processBuilder.start();
        String title = "";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            while((title = br.readLine()) != null){
                if(title.contains("format.tags.title"))
                    return title.substring(title.indexOf('=') + 1).replace("\"", "");
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
        int exitValue = process.waitFor();
        if(exitValue != 0) throw new InterruptedException();
        return title;
    }
}
