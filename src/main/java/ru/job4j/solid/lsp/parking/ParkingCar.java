package ru.job4j.solid.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingCar implements Parking {

    private int countMotorCar;
    private int countFreightCar;
    private final List<Car> placeCar;

    public ParkingCar(int countMotorCar, int countFreightCar) {
        this.countMotorCar = countMotorCar;
        this.countFreightCar = countFreightCar;
        this.placeCar = new ArrayList<>(countFreightCar + countFreightCar);
    }

    @Override
    public boolean addToParkingSpace(Car car) {
        boolean res = false;
        if (car.getSize() == MotorCar.SIZE && countMotorCar >= MotorCar.SIZE) {
            placeCar.add(car);
            countMotorCar--;
            res = true;
        } else if (car.getSize() > MotorCar.SIZE && countFreightCar >= MotorCar.SIZE) {
            placeCar.add(car);
            countFreightCar--;
            res = true;
        } else if (car.getSize() > MotorCar.SIZE && countMotorCar >= car.getSize()) {
            placeCar.add(car);
            countMotorCar += car.getSize();
            res = true;
        }
        return res;
    }

    @Override
    public List<Car> findAllParkingCar() {
        return placeCar.stream().toList();
    }
}
