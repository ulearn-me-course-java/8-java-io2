package com.example.task01;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Task01Main {

    private static final long MAX_TIME = 10_000;

    private static final String NEEDED_KEY = "format.tags.title";

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        // your implementation here
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        try {
            Process process = processBuilder.start();
            boolean executedOnTime = process.waitFor(MAX_TIME, TimeUnit.MILLISECONDS);

            if (!executedOnTime) {
                System.out.println("Failed, out of timeout.");
                process.destroy();

                return null;
            }

            if (process.exitValue() != 0) {
                System.out.println("Failed with exit code" + process.exitValue());

                return null;
            }

            BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;

            while ((line = output.readLine()) != null) {
                String[] lineArr = line.split("=");
                if (lineArr.length == 2 && lineArr[0].equals(NEEDED_KEY)) {

                    return lineArr[1].substring(1, lineArr[1].length() - 1);
                }
            }

        } catch (IOException e) {
            System.out.println("Process was not started \n" + e);

            return null;
        } catch (InterruptedException e) {
            System.out.println("Process was interrupted \n" + e);
            Thread.currentThread().interrupt();

            return null;
        }
        return null;
    }
}
