package ru.job4j.collections.list.mytest;

public interface MyTestLinkedList<E> extends Iterable<E> {

    void addFirst(E value);

    void addLast(E value);

    void insert(E value);

    E deleteFirst();

    E deleteLast();

    void remove(E value);

    boolean isEmpty();

    E getFirst();

    E getLast();

    void revers();

    boolean contains(E value);

    void display();

    int size();

}
