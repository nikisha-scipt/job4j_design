package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {

        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                if (read % 2 == 0) {
                    stringBuilder.append((char) read);
                }
            }
            String[] lines = stringBuilder.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
