package ru.job4j.solid.lsp.parking;

import java.util.List;

public interface Parking {

    boolean addToParkingSpace(Car car);

    List<Car> findAllParkingCar();

}
