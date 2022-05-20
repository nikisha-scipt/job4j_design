package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

public class ReadFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // буферизированные обертки
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.lines().forEach(System.out::println);
            // equivalent
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
