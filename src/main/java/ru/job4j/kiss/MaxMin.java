package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> predicate = (t, t2) -> comparator.compare(t, t2) < 0;
        return kissMethod(value, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> predicate = (t, t2) -> comparator.compare(t, t2) > 0;
        return kissMethod(value, predicate);
    }

    private <T> T kissMethod(List<T> value, BiPredicate<T, T> predicate) {
        T result = value.get(0);
        for (T elem : value) {
            if (predicate.test(result, elem)) {
                result = elem;
            }
        }
        return result;
    }


}
