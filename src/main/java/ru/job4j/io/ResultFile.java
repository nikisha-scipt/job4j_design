package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultFile {

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] arr = multiple(5);
            out.write("It is multiple:".getBytes());
            out.write(System.lineSeparator().getBytes());
            for (int[] ints : arr) {
                out.write("\t\t".getBytes());
                for (int anInt : ints) {
                    out.write((anInt + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Буфферизированная запись в файл
         */
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("result.txt")))) {
            int[][] arr = multiple(5);
            out.println("It is multiple:");
            for (int[] ints : arr) {
                out.println("\t\t");
                for (int anInt : ints) {
                    out.print((anInt + " "));
                }
                out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int[][] multiple(int size) {
        int[][] res = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[i][j] = (i + 1) * (j + 1);
            }
        }
        return res;
    }
}
