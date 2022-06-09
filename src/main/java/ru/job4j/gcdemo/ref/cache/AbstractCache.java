package ru.job4j.gcdemo.ref.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.putIfAbsent(key, new SoftReference<>(value));
    }

    public V get(K key) throws IOException {
        return cache.getOrDefault(key, new SoftReference<>(load(key))).get();
    }

    protected abstract V load(K key) throws IOException;

}
