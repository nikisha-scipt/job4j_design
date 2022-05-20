package ru.job4j.list.mytest;

import java.util.*;

public class MySimpleArrayList<E> implements MyTestArrayList<E> {

    private int size;
    private int modeCount;
    private E[] container;

    public MySimpleArrayList() {
        this.container = (E[]) new Object[10];
    }

    public MySimpleArrayList(int capacity) {
        this.container = (E[]) new Object[capacity];
    }

    public MySimpleArrayList(E[] col) {
        this.container = (E[]) new Object[col.length];
        this.size = col.length;
        System.arraycopy(col, 0, this.container, 0, col.length);
    }

    @Override
    public void add(E value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modeCount++;
    }

    @Override
    public void add(int index, E value) {
        Objects.checkIndex(index, size);
        if (size == container.length) {
            grow();
        }
        System.arraycopy(container, index, container, index + 1, size);
        container[index] = value;
        size++;
        modeCount++;
    }

    private void grow() {
        container = container.length != 0 ? Arrays.copyOf(container, container.length * 2) : Arrays.copyOf(container, 10);
    }

    @Override
    public boolean deleteValue(E value) {
        boolean check = false;
        if (contains(value)) {
            int index = indexOf(value);
            System.arraycopy(container, index + 1, container, index, size - index - 1);
            check = true;
            size--;
            modeCount++;
            container[container.length - index] = null;
        }
        return check;
    }

    @Override
    public E deleteIndex(int index) {
        Objects.checkIndex(index, size);
        E value = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        modeCount++;
        container[size - index] = null;
        return value;
    }

    @Override
    public void replace(int index, E value) {
        Objects.checkIndex(index, size);
        container[index] = value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public boolean contains(E value) {
        boolean icCheck = false;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, container[i])) {
                icCheck = true;
                break;
            }
        }
        return icCheck;
    }

    @Override
    public boolean removeAll(E[] coll) {
        boolean isCheck = false;
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < coll.length; j++) {
                if (Objects.equals(container[i], coll[j])) {
                    System.arraycopy(container, i + 1, container, i, size - i - 1);
                    container[container.length - i - j] = null;
                    count++;
                    size--;
                }
            }
        }
        if (count == coll.length) {
            isCheck = true;
        }
        return isCheck;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int indexOf(E value) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(container[i], value)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int pointer;
            private final int modCountCheck = modeCount;

            @Override
            public boolean hasNext() {
                if (modCountCheck != modeCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[pointer++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i + 1 != size) {
                sb.append(container[i]);
                sb.append(", ");
                continue;
            }
            sb.append(container[i]);
        }
        return sb.append("]").toString();
    }

}
