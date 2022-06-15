package ru.job4j.solid.lsp.parking;

import java.util.Objects;

public abstract class Car {

    private String type;
    private int size;

    public Car(String type, int size) {
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return size == car.size && Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, size);
    }

    @Override
    public String toString() {
        return "Car{" + "type='" + type + '\'' + ", size=" + size + '}';
    }
}
