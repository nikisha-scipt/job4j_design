package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        boolean check = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            writer.println("Результат:");
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] temp = line.split(" ");
                if ("400".equals(temp[0]) || "500".equals(temp[0])) {
                    if (check) {
                        writer.print(temp[1] + ";");
                        check = false;
                    }
                } else if (!check) {
                    check = true;
                    writer.println(temp[1] + ";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analysis analysis = new Analysis();
        analysis.unavailable("analys.txt", "result.txt");
    }

}
