package ru.job4j.io;

import java.io.FileOutputStream;

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
        } catch (Exception e) {
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
