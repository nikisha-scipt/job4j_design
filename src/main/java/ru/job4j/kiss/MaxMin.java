package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return kissMethod(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return kissMethod(value, comparator.reversed());
    }

    private <T> T kissMethod(List<T> value, Comparator<T> comparator) {
        T result = value.get(0);
        for (T elem : value) {
            if (comparator.compare(result, elem) < 0) {
                result = elem;
            }
        }
        return result;
    }


}
