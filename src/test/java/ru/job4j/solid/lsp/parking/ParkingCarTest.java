package ru.job4j.solid.lsp.parking;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class ParkingCarTest {

    @Test
    public void whenParkingMotorCar() {
        Car motorCar = new MotorCar("Passenger");
        ParkingCar parkingCar = new ParkingCar(1, 0);
        parkingCar.addToParkingSpace(motorCar);
        assertThat(parkingCar.findAllParkingCar(), is(List.of(new MotorCar("Passenger"))));
    }

    @Test
    public void whenParkingFreightCar() {
        Car freightCar = new FreightCar("Freight", 2);
        ParkingCar parkingCar = new ParkingCar(0, 1);
        assertTrue(parkingCar.addToParkingSpace(freightCar));
    }

    @Test
    public void whenParkingMotorCarAndFreightCar() {
        Car motorCar = new MotorCar("Passenger");
        Car freightCar = new FreightCar("Freight", 2);
        ParkingCar parkingCar = new ParkingCar(1, 1);
        parkingCar.addToParkingSpace(motorCar);
        parkingCar.addToParkingSpace(freightCar);
        assertThat(parkingCar.findAllParkingCar(), is(List.of(
                new MotorCar("Passenger"),
                new FreightCar("Freight", 2)
                )));
    }

    @Test
    public void whenNotSizeAtParkingForMotorCar() {
        Car motorCar = new MotorCar("Passenger");
        ParkingCar parkingCar = new ParkingCar(0, 0);
        assertFalse(parkingCar.addToParkingSpace(motorCar));
    }

    @Test
    public void whenNotSizeAtParkingForFreightCar() {
        Car motorCar = new FreightCar("Freight", 2);
        ParkingCar parkingCar = new ParkingCar(0, 0);
        assertFalse(parkingCar.addToParkingSpace(motorCar));
    }

    @Test
    public void whenMoreCarThanSizeOfParking() {
        Car motorCar = new MotorCar("Passenger");
        Car freightCar = new FreightCar("Freight", 2);
        Car freightCar2 = new FreightCar("Freight2", 2);
        ParkingCar parkingCar = new ParkingCar(1, 1);
        assertTrue(parkingCar.addToParkingSpace(motorCar));
        assertTrue(parkingCar.addToParkingSpace(freightCar));
        assertFalse(parkingCar.addToParkingSpace(freightCar2));
    }

    @Test
    public void whenFreightCarParkingAtMotorPlace() {
        Car freightCar = new FreightCar("Freight", 3);
        ParkingCar parkingCar = new ParkingCar(3, 0);
        assertTrue(parkingCar.addToParkingSpace(freightCar));
    }

    @Test(expected = AssertionError.class)
    public void whenFreightCarParkingAtMotorPlaceAndLessPlacesForBigCar() {
        Car freightCar = new FreightCar("Freight", 3);
        ParkingCar parkingCar = new ParkingCar(1, 0);
        assertTrue(parkingCar.addToParkingSpace(freightCar));
    }


}