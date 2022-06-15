package ru.job4j.collections.list;

public interface MyList<T> extends Iterable<T>  {

    void add(T value);

    T set(int index, T newValue);

    T remove(int index);

    T get(int index);

    int size();

}
