package ru.job4j.collections.map;

import java.util.*;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", children=" + children + ", birthday=" + birthday + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User first = new User("Danil", 2, new GregorianCalendar(2022, Calendar.APRIL, 15));
        User second = new User("Danil", 2, new GregorianCalendar(2022, Calendar.APRIL, 15));
        Map<User, Object> users = new HashMap<>();
        users.put(first, new Object());
        users.put(second, new Object());
        System.out.println(users);
    }

}
