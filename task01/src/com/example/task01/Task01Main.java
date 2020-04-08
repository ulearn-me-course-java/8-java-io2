package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException{
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException{
        ProcessBuilder processBuilder=new ProcessBuilder();
        processBuilder.command("ffprobe", "-v","error", "-of", "flat", "-show_format",file.toString())
                .redirectOutput(ProcessBuilder.Redirect.PIPE);
        Process process=processBuilder.start();
        try(BufferedReader reader=new BufferedReader(
                new InputStreamReader(process.getInputStream()))){
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains("format.tags.title"))
                    return line.substring(19,line.length()-1);
            }
        }catch (IOException ex){
            ex.getMessage();
        }
        return null;
    }
}
