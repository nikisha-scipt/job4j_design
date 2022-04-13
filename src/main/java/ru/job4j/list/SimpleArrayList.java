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

    public SimpleArrayList(T[] arr) {
        this.container = (T[]) new Object[arr.length];
        System.arraycopy(arr, 0, container, 0, arr.length);
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
        container = container.length != 0 ? Arrays.copyOf(container, container.length * 2) : Arrays.copyOf(container, 10);
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
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        modCount++;
        size--;
        container[container.length - index] = null;
        return value;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private int getDefaultCapacity() {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");
        Iterator<T> it = iterator();
        int check = 0;
        while (it.hasNext()) {
            if (check == size - 1) {
                sb.append(it.next());
                break;
            }
            sb.append(it.next()).append(", ");
            check++;
        }
        return sb.append(" }").toString();
    }

}
