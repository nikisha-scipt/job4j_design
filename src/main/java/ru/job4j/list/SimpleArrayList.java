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
        this.defaultCapacity = capacity;
        this.container = (T[]) new Object[defaultCapacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    private void grow() {
        container = container.length != 0 ? Arrays.copyOf(container, container.length * 2) : Arrays.copyOf(container, container.length);
    }

    @Override
    public T set(int index, T newValue) {
        T value = get(index);
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        T value = get(index);
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

    public int getDefaultCapacity() {
        return defaultCapacity;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            final int expectedModCount = modCount;
            private int pointer;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No has element");
                }
                return container[pointer++];
            }
        };
    }

}
