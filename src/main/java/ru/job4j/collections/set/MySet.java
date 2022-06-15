package ru.job4j.collections.set;

public interface MySet<E> extends Iterable<E> {

    boolean add(E value);

    boolean contains(E value);

    int size();

}
