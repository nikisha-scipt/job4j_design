package ru.job4j.list;

public interface MyQueue<E> extends Iterable<E> {

    void push(E value);

    E poll();

    boolean isEmpty();


}
