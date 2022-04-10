package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        for (Map.Entry<String, T> entry : storage.entrySet()) {
            if (entry.getKey().equals(model.getId())) {
                return;
            }
        }
        storage.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (Map.Entry<String, T> entry: storage.entrySet()) {
            if (entry.getKey().equals(id)) {
                entry.setValue(model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (storage.containsKey(id)) {
            storage.remove(id);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T model = null;
        for (Map.Entry<String, T> entry : storage.entrySet()) {
            if (entry.getKey().equals(id)) {
                model = entry.getValue();
                break;
            }
        }
        return model;
    }
}
