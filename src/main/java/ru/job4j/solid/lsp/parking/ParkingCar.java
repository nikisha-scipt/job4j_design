package ru.job4j.solid.lsp.parking;

import java.util.List;

public class ParkingCar implements Parking {

    private int countMotorCar;
    private int countFreightCar;
    private List<Car> placeCar;

    public ParkingCar(int countMotorCar, int countFreightCar, List<Car> placeCar) {
        this.countMotorCar = countMotorCar;
        this.countFreightCar = countFreightCar;
        this.placeCar = placeCar;
    }

    @Override
    public boolean addToParkingSpace(Car car) {
        boolean res = false;
        if (car.getSize() == 1 && countMotorCar > 0) {
            placeCar.add(car);
            countMotorCar--;
            res = true;
        } else if (car.getSize() > 1 && countFreightCar > 0) {
            placeCar.add(car);
            countFreightCar--;
            res = true;
        }
        return res;
    }

    @Override
    public List<Car> findAllParkingCar() {
        return placeCar;
    }
}
