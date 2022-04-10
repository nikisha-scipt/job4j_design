package ru.job4j.generics;

import java.util.Objects;

public class Animal {

    private String name;
    private Integer age;
    private String color;

    public Animal(String name, Integer age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) && Objects.equals(age, animal.age) && Objects.equals(color, animal.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, color);
    }

    @Override
    public String toString() {
        return "Animal{" + "name='" + name + '\'' + ", age=" + age + ", color='" + color + '\'' + '}';
    }
}
