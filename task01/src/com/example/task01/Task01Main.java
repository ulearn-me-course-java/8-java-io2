package com.example.task01;

import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ls","-l")
                .directory(new File("C:\\Users\\gabid\\OneDrive\\Рабочий стол\\ffmpeg-2021-04-04-git-b1b7cc698b-full_build\\bin"))
                .command("cmd.exe", "/c", "ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath())
                .redirectOutput(ProcessBuilder.Redirect.PIPE); //контролируем поток вывода

        Process process = processBuilder.start();
        BufferedReader  reader = new BufferedReader( new InputStreamReader(process.getInputStream()));
        String result = reader.readLine();
        while(result != null){
            if(result.contains("title")){
                return result.substring(result.indexOf("\"")+1, result.lastIndexOf("\""));
            }
            result = reader.readLine();
        }
        return "sound name";
    }
}
