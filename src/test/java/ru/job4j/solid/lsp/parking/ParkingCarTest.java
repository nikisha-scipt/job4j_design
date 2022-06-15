package ru.job4j.solid.lsp.parking;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingCarTest {

    @Test
    public void whenParkingMotorCar() {
        Car motorCar = new MotorCar("Passenger", 1);
        ParkingCar parkingCar = new ParkingCar(1, 0, new ArrayList<>());
        parkingCar.addToParkingSpace(motorCar);
        assertThat(parkingCar.findAllParkingCar(), is(List.of(new MotorCar("Passenger", 1))));
    }

    @Test
    public void whenParkingFreightCar() {
        Car freightCar = new FreightCar("Freight", 2);
        ParkingCar parkingCar = new ParkingCar(0, 1, new ArrayList<>());
        assertTrue(parkingCar.addToParkingSpace(freightCar));
    }

    @Test
    public void whenParkingMotorCarAndFreightCar() {
        Car motorCar = new MotorCar("Passenger", 1);
        Car freightCar = new FreightCar("Freight", 2);
        ParkingCar parkingCar = new ParkingCar(1, 1, new ArrayList<>());
        parkingCar.addToParkingSpace(motorCar);
        parkingCar.addToParkingSpace(freightCar);
        assertThat(parkingCar.findAllParkingCar(), is(List.of(
                new MotorCar("Passenger", 1),
                new FreightCar("Freight", 2)
                )));
    }

    @Test
    public void whenNotSizeAtParkingForMotorCar() {
        Car motorCar = new MotorCar("Passenger", 1);
        ParkingCar parkingCar = new ParkingCar(0, 0, new ArrayList<>());
        assertFalse(parkingCar.addToParkingSpace(motorCar));
    }

    @Test
    public void whenNotSizeAtParkingForFreightCar() {
        Car motorCar = new FreightCar("Freight", 2);
        ParkingCar parkingCar = new ParkingCar(0, 0, new ArrayList<>());
        assertFalse(parkingCar.addToParkingSpace(motorCar));
    }

    @Test
    public void whenMoreCarThanSizeOfParking() {
        Car motorCar = new MotorCar("Passenger", 1);
        Car freightCar = new FreightCar("Freight", 2);
        Car freightCar2 = new FreightCar("Freight2", 2);
        ParkingCar parkingCar = new ParkingCar(1, 1, new ArrayList<>());
        assertTrue(parkingCar.addToParkingSpace(motorCar));
        assertTrue(parkingCar.addToParkingSpace(freightCar));
        assertFalse(parkingCar.addToParkingSpace(freightCar2));
    }


}