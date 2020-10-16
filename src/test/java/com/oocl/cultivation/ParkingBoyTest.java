package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_parking_boy_park_car_given_car() {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_right_car_ticket_when_parking_boy_fetch_car_given_ticket() {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        assertSame(car, fetchedCar);
    }

    @Test
    void should_return_right_cars_when_parking_boy_fetch_cars_given_tickets() {
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        Car fetchedCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);

        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    void should_return_no_car_when_parking_boy_fetch_cars_given_wrong_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket ticket = new ParkingTicket();

        Car fetchedCar = parkingBoy.fetch(ticket);

        assertNull(fetchedCar);
    }

    @Test
    void should_return_no_car_when_parking_boy_fetch_car_given_no_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        Car fetchedCar = parkingBoy.fetch(null);

        assertNull(fetchedCar);
    }

    @Test
    void should_return_no_car_when_parking_boy_fetch_car_given_used_ticket() {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket usedTicket = parkingBoy.park(car);

        parkingBoy.fetch(usedTicket);
        Car fetchedCar = parkingBoy.fetch(usedTicket);

        assertNull(fetchedCar);
    }
}
