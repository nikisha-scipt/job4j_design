package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {

    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        if (data.length == 0) {
            throw new NoSuchElementException();
        } else {
            this.data = data;
            this.point = data.length - 1;
        }
    }

    @Override
    public boolean hasNext() {
        return data.length > point;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }

}
