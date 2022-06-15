package ru.job4j.collections.list.mytest;

public interface MyTestArrayList<E> extends Iterable<E> {

    void add(E value);

    void add(int index, E value);

    boolean deleteValue(E value);

    E deleteIndex(int index);

    void replace(int index, E value);

    int size();

    void display();

    boolean contains(E value);

    boolean removeAll(E[] coll);

    E get(int index);

    int indexOf(E value);

}
