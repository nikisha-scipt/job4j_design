package ru.job4j.gcdemo;

import java.util.*;

public class User {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
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
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %s %d%n", name, age);
    }

    private static void info() {
        long memory = 1000 * 1000;
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long maxMemory = runtime.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / memory);
        System.out.printf("Total: %d%n", totalMemory / memory);
        System.out.printf("Max: %d%n", maxMemory / memory);
    }

    public static void main(String[] args) {
        String[] names = {"Danil", "Danya", "Andrey", "Anton", "Maria", "Oleg", "Maxim"};
        info();
        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0) {
                new User();
            } else {
                new User(names[(int) (Math.random() * names.length)], (int) (Math.random() * 100));
            }
        }
        info();
    }
}
