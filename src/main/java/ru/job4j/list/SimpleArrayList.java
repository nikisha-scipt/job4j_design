package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;
    private int defaultCapacity;

    public SimpleArrayList() {
        this.defaultCapacity = 10;
        this.container = (T[]) new Object[defaultCapacity];
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T value = container[index];
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T value = container[index];
        container[index] = null;
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        modCount++;
        size--;
        return value;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            final int expectedModCount = modCount;
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer < size;
            }

            @Override
            public T next() {

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("No has element");
                }
                return container[pointer++];
            }
        };
    }

}
