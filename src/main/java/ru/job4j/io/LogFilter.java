package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {

    public List<String> filter(String file) {
        List<String> res = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String[] temp;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                temp = line.split(" ");
                if ("404".equals(temp[temp.length - 2])) {
                    res.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String elem : log) {
                out.println(elem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
        log.forEach(System.out::println);
    }

}
