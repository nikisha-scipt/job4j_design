package ru.job4j.kiss;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MaxMinTest {

    private MaxMin maxMin;
    private Comparator<Integer> comparator;
    private List<Integer> list;

    @Before
    public void init() {
        maxMin = new MaxMin();
        comparator = Integer::compareTo;
        list = List.of(1, 2, 6, 7, 2, 15);
    }

    @Test
    public void whenMaxInList15() {
        Integer res = maxMin.max(list, comparator);
        int expected = 15;
        assertThat(expected, is(res));
    }

    @Test
    public void whenMinInList15() {
        Integer res = maxMin.min(list, comparator);
        int expected = 1;
        assertThat(expected, is(res));
    }


}