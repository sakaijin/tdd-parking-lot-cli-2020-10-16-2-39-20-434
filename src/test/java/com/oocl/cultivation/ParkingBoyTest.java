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
    void should_return_right_car_ticket_when_parking_boy_fetch_car_given_ticket() throws NoTicketException {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        assertSame(car, fetchedCar);
    }

    @Test
    void should_return_right_cars_when_parking_boy_fetch_cars_given_tickets() throws NoTicketException {
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
    void should_return_unrecognized_parking_ticket_when_parking_boy_fetch_cars_given_wrong_ticket() throws NoTicketException {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        parkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        assertThrows(UnrecognizedParkingTicket.class, () -> parkingBoy.fetch(wrongTicket));
    }

    @Test
    void should_return_no_ticket_exception__when_parking_boy_fetch_car_given_no_ticket(){
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        parkingBoy.park(car);

        assertThrows(NoTicketException.class, () -> parkingBoy.fetch(null));
    }

    @Test
    void should_return_no_car_when_parking_boy_fetch_car_given_used_ticket() throws NoTicketException {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket usedTicket = parkingBoy.park(car);

        parkingBoy.fetch(usedTicket);
        Car fetchedCar = parkingBoy.fetch(usedTicket);

        assertNull(fetchedCar);
    }

    @Test
    void should_return_no_parking_ticket_when_parking_boy_parks_car_given_parking_lot_capacity_full() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setSampleCapacity(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(ticket);
    }
}
