package ru.job4j.collections.list;

public interface MyQueue<E> extends Iterable<E> {

    void push(E value);

    E poll();

    boolean isEmpty();


}
