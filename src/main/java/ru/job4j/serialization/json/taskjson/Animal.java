package ru.job4j.serialization.json.taskjson;

import java.util.Objects;

public class Animal {

    private String type;

    public Animal(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animal{" + "type='" + type + '\'' + '}';
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
        return Objects.equals(type, animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    public String getType() {
        return type;
    }
}
