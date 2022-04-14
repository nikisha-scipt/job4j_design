package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;


public class SimpleSet<E> implements MySet<E> {

    private SimpleArrayList<E> set = new SimpleArrayList<>();
    private int size;

    @Override
    public boolean add(E value) {
        boolean check = contains(value);
        boolean isCheck = false;
        if (!check) {
            set.add(value);
            isCheck = true;
            size++;
        }
        return isCheck;
    }

    @Override
    public boolean contains(E value) {
        boolean checkNull = value != null;
        boolean checkContains = false;
        Iterator<E> iterator = set.iterator();
        if (checkNull) {
            while (iterator.hasNext()) {
                if (value.equals(iterator.next())) {
                    checkContains = true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    checkContains = true;
                }
            }
        }
        return checkContains;
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
