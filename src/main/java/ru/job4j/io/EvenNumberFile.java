package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {

        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder str = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                str.append((char) read);
            }
            String[] arr = str.toString().split(System.lineSeparator());
            for (String s : arr) {
                int x = Integer.parseInt(s);
                if (x % 2 == 0) {
                    System.out.println(x);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
