package ru.job4j.generics;

import java.util.*;

public class GenericUsage {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add(1);
        list.add(new Person("Danil", 26, new Date()));

        for (Object o : list) {
            if (o instanceof Integer) {
                Integer in = (Integer) o;
                System.out.println("Текущий элемент: " + in);
            } else if (o instanceof Person) {
                Person person = (Person) o;
                System.out.println("Текущий элемент: " + person);
            } else {
                String s = (String) o;
                System.out.println("Текущий элемент: " + s);
            }
        }

        List<Integer> intList = List.of(1, 2, 3, 4, 5, 6);
        printRsl(intList);

        List<Person> listPerson = List.of(
                new Person("Petr", 36, new Date()),
                new Person("Andrey", 28, new Date())
        );
        List<Programmer> listProger = List.of(
                new Programmer("Danil", 26, new Date()),
                new Programmer("Nastya", 18, new Date())
        );
        printPersonOrProgrammer(listPerson);
        printPersonOrProgrammer(listProger);
    }

    /**
     * WildCard <?>
     * принимает любой конкретный тип
     * @param col
     */
    public static void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    /**
     * Bounded WildCard
     * принимает все типы данных от класса родителя Person
     * @param col
     */
    public static void printPersonOrProgrammer(Collection<? extends Person> col) {
        for (Iterator<? extends Person> it = col.iterator(); it.hasNext();) {
            Person next = it.next();
            System.out.println(next.getClass().getName() + ": " + next);
        }
    }

    /**
     * Lower bounded Wildcard
     * принимает все типы данных Integer и классы, от которых идет (выше) - Object, Number
     * @param list
     */
    public static void addAll(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }

        for (Object o : list) {
            System.out.println(o);
        }
    }

}
