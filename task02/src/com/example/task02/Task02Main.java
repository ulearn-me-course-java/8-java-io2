package com.example.task02;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.nio.file.Files;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));
        */

    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> paths = new LinkedList<>();
        Files.walk(rootDir)//поток, который проходится по дереву с начальной позиции rootDir
                .filter(Files::isRegularFile)// является ли файл обычным файлом
                .forEach(paths::add);//естественный способ обхода файлов
        return paths;
    }
}
