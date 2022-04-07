package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return column < data[row].length || row < data.length - 1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while ((column >= data[row].length)) {
            row++;
            column = 0;
        }
        return data[row][column++];
    }


    public static void main(String[] args) {
        int[][] arr = {{}, {}};
        MatrixIt matrixIt = new MatrixIt(arr);
        System.out.println(matrixIt.hasNext());

    }
}
