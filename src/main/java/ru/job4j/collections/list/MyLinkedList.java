package ru.job4j.collections.list;

public interface MyLinkedList<E> extends Iterable<E> {

    void add(E value);

    E get(int index);

    E deleteFirst();

    boolean isEmpty();

}
