package ru.job4j.list.mytest;

import org.junit.Before;
import org.junit.Test;

public class MySimpleLinkedListTest {

    private ru.job4j.list.mytest.MyTestLinkedList<Integer> list;

    @Before
    public void createList() {
        list = new MySimpleLinkedList<>();
    }

    @Test
    public void whenDisplayList() {
        list.insert(1);
        list.insert(2);
        list.insert(3);
        System.out.println(list);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        list.addFirst(5);
        System.out.println(list);
        list.addLast(10);
        System.out.println(list);
        System.out.println(list.deleteFirst());
        System.out.println(list);
        System.out.println(list.deleteLast());
        System.out.println(list);
        list.revers();
        System.out.println(list);

    }

}