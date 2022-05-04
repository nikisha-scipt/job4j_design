package ru.job4j.io;

import java.io.File;

public class Dir {

    public static void main(String[] args) {
        File file = new File("D:\\java\\job4j\\project");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.printf("Name of file - %s\nSize of file - %d\n**************\n", subfile.getName(), subfile.length());
        }
    }

    private static void viewFile(File file, int count) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t".repeat(count));

        if (file.isFile()) {
            System.out.println(stringBuilder + "File" + file.getName());
        } else {
            System.out.println(stringBuilder + "Dir" + file.getName());
            count++;
            for (File list : file.listFiles()) {
                viewFile(list, count);
            }
        }
    }

}
