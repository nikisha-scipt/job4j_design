package ru.job4j.collections.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * CRUD - create read update delete
 * 0.1. Интерфейс List [#4953]
 */
public class ListUsage {

    /**
     * add(elem) - add(index,elem) - addAll(Collection<? extends E> c) - addAll(index, Collection<? extends E> c)
     * @param args
     */
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
        rsl.add(1, "four");
        List<String> list = new ArrayList<>();
        list.add("five");
        list.add("six");
        rsl.addAll(list);
        for (String s : rsl) {
            System.out.println(s);
        }

        /**
         * Read list
         * for - iterator - listIterator (begin and end point && add elem)
         */
        for (int i = 0; i < rsl.size(); i++) {
            System.out.print(rsl.get(i) + " ");
        }
        System.out.println();
        for (Iterator<String> it = rsl.iterator(); it.hasNext();) {
            System.out.print(it.next() + " ");
        }

        System.out.println();
        for (ListIterator<String> listIterator = rsl.listIterator(); listIterator.hasNext();) {
            System.out.print(listIterator.next() + " ");
        }

        System.out.println();
        rsl.replaceAll(String::toUpperCase);
        for (ListIterator<String> listIterator = rsl.listIterator(); listIterator.hasNext();) {
            System.out.print(listIterator.next() + " ");
        }
    }

}
