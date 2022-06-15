package ru.job4j.collections.set;

import ru.job4j.collections.list.SimpleArrayList;
import java.util.Iterator;
import java.util.Objects;


public class SimpleSet<E> implements MySet<E> {

    private SimpleArrayList<E> set = new SimpleArrayList<>();
    private int size;

    @Override
    public boolean add(E value) {
        boolean check = false;
        if (!contains(value)) {
            set.add(value);
            check = true;
            size++;
        }
        return check;
    }

    @Override
    public boolean contains(E value) {
        boolean check = false;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(set.get(i), value)) {
                check = true;
                break;
            }
        }
        return check;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    public static void main(String[] args) {
        SimpleSet<Integer> mySet = new SimpleSet<>();
        System.out.println(mySet.add(null));
        System.out.println(mySet.add(null));
        mySet.add(2);
        Iterator<Integer> iterator = mySet.iterator();
    }
}
