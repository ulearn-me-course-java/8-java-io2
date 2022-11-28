package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException
    {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("D:\\ffm\\bin\\ffprobe.exe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());
        Process process = processBuilder.start();
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())))
        {
            while ((line = reader.readLine()) != null)
            {
                if (line.contains("title"))
                    return getTitle(line);
            }
        }
        return "Name not found";
    }

    private static String getTitle(String str)
    {
        char[] arr = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        boolean firstSymIsFound = false;
        for (int i = 0; i < str.length(); i++)
        {
            if (!firstSymIsFound && arr[i] == '"') firstSymIsFound = true;
            else if (firstSymIsFound && arr[i] != '"') builder.append(arr[i]);
            else if (firstSymIsFound) break;
        }
        return builder.toString();
    }
}
