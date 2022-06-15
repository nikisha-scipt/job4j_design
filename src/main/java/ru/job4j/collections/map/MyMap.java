package ru.job4j.collections.map;

public interface MyMap<K, V> extends Iterable<K> {

    boolean put(K key, V value);

    V get(K key);

    boolean remove(K key);

}
