package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutElement() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("ss", 5);
        assertThat(simpleMap.get("ss"), is(5));
    }

    @Test
    public void whenPutAndGetElement() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("ss", 5);
        assertTrue(simpleMap.put("ss", 8));
        assertThat(simpleMap.get("ss"), is(8));
    }

    @Test
    public void whenGetElement() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertNull(simpleMap.get("ss"));
    }

    @Test
    public void whenDeleteElement() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put("ss", 5));
        assertThat(simpleMap.get("ss"), is(5));
        assertTrue(simpleMap.remove("ss"));
        assertNull(simpleMap.get("ss"));
    }

    @Test
    public void whenIteratorNext() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put(1, 2);
        simpleMap.put(2, 7);
        simpleMap.put(3, 8);
        simpleMap.put(4, 9);
        Iterator<Integer> it = simpleMap.iterator();
        assertThat(it.hasNext(), Is.is(true));
        assertThat(it.next(), Is.is(1));
        assertThat(it.hasNext(), Is.is(true));
        assertThat(it.next(), Is.is(2));
        assertThat(it.hasNext(), Is.is(true));
        assertThat(it.next(), Is.is(3));
        assertThat(it.hasNext(), Is.is(true));
        assertThat(it.next(), Is.is(4));
    }

}