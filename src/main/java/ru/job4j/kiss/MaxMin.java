package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        T res = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (comparator.compare(res, value.get(i)) > 0) {
                res = value.get(i);
            }
        }
        return res;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        T res = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (comparator.compare(res, value.get(i)) < 0) {
                res = value.get(i);
            }
        }
        return res;
    }


}
