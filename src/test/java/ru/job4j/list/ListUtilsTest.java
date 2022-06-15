package ru.job4j.list;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.job4j.collections.list.listiterator.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, Is.is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfElementMore2() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeIf(input, e -> e > 2);
        assertThat(input, Is.is(Arrays.asList(1, 2)));
    }

    @Test
    public void whenReplaceIfFirstElementLess2() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.replaceIf(input, elem -> elem < 2, 5);
        assertThat(input, Is.is(Arrays.asList(5, 2, 3)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elem = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, elem);
        assertThat(input, Is.is(Arrays.asList(4, 5)));
    }

}