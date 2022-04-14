package ru.job4j.list;

public interface MyLinkedList<E> extends Iterable<E> {

    void add(E value);

    E get(int index);

    E deleteFirst();

    boolean isEmpty();

}
