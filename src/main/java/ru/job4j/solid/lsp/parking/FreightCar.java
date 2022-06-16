package ru.job4j.solid.lsp.parking;

public class FreightCar extends Car {

    public FreightCar(String type, int size) {
        super(type, size);
        if (size <= MotorCar.SIZE) {
            throw new IllegalArgumentException("Wrong size");
        }
    }
}
