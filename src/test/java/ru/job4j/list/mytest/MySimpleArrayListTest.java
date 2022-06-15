package ru.job4j.list.mytest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.collections.list.mytest.MySimpleArrayList;

public class MySimpleArrayListTest {

    private MySimpleArrayList<Integer> arrayList;

    @Before
    public void createArrayList() {
        arrayList = new MySimpleArrayList<>();
    }

    @Test
    public void whenAddNewValueInArray() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        Assert.assertEquals(3, arrayList.size());
    }

    @Test
    public void whenAddNewValueInArrayAtIndex() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(2, 5);
        Assert.assertEquals(4, arrayList.size());
    }

    @Test
    public void whenDeleteValue() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(2, 5);
        arrayList.deleteValue(2);
        Assert.assertEquals(3, arrayList.size());
    }

    @Test
    public void whenDeleteValueAtIndex() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(2, 5);
        arrayList.deleteIndex(1);
        Assert.assertEquals(3, arrayList.size());
    }

    @Test
    public void whenDeleteAll() {
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(4);
        arrayList.add(5);
        Integer[] arrDel = new Integer[] {4, 5};
        arrayList.removeAll(arrDel);
        System.out.println(arrayList);
        Assert.assertEquals(3, arrayList.size());
    }

}