package ru.job4j.list;

public interface MyStack<E> extends Iterable<E> {

    void push(E value);

    E pop();

    boolean isEmpty();

}
