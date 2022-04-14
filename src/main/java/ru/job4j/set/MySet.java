package ru.job4j.set;

public interface MySet<E> extends Iterable<E> {

    boolean add(E value);

    boolean contains(E value);

    int size();

}
