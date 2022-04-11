package ru.job4j.generics;

public interface Store<T extends Base> {

    void add(T model);

    T replace(String id, T model);

    T delete(String id);

    T findById(String id);

}
