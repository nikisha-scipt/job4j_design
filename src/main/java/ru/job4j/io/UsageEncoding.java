package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;

public class UsageEncoding {

    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().map(s -> s + System.lineSeparator()).forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDataInFile(String path, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            writer.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./garbage/text.txt";
        UsageEncoding usage = new UsageEncoding();
        String s = usage.readFile(path);
        System.out.println("Data:");
        System.out.println(s);
        usage.writeDataInFile("test.txt", s);
    }

}
