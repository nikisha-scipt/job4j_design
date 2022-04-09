package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int temp = -1;
        for (int i = this.index; i < this.data.length; i++) {
            if (this.data[i] % 2 == 0) {
                this.index = i;
                temp++;
                break;
            }
        }
        return temp == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.data[this.index++];
    }

}
