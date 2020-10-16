package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_parking_boy_park_car_given_car() throws NotEnoughPosition {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_right_car_ticket_when_parking_boy_fetch_car_given_ticket() throws NoTicketException, UnrecognizedParkingTicket, NotEnoughPosition {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        assertSame(car, fetchedCar);
    }

    @Test
    void should_return_right_cars_when_parking_boy_fetch_cars_given_tickets() throws NoTicketException, UnrecognizedParkingTicket, NotEnoughPosition {
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
    void should_return_unrecognized_parking_ticket_when_parking_boy_fetch_cars_given_wrong_ticket() throws NotEnoughPosition {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        parkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        assertThrows(UnrecognizedParkingTicket.class, () -> parkingBoy.fetch(wrongTicket));
    }

    @Test
    void should_return_no_ticket_exception__when_parking_boy_fetch_car_given_no_ticket() throws NotEnoughPosition {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        parkingBoy.park(car);

        assertThrows(NoTicketException.class, () -> parkingBoy.fetch(null));
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_parking_boy_fetch_car_given_used_ticket() throws NoTicketException, UnrecognizedParkingTicket, NotEnoughPosition {
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket usedTicket = parkingBoy.park(car);

        parkingBoy.fetch(usedTicket);

        assertThrows(UnrecognizedParkingTicket.class, () -> parkingBoy.fetch(usedTicket));
    }

    @Test
    void should_return_no_parking_ticket_when_parking_boy_parks_car_given_parking_lot_capacity_full() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1,1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        assertThrows(NotEnoughPosition.class, () -> parkingBoy.park(car));
    }

    @Test
    void should_return_empty_lot_parked_car_1__when_parking_boy_parks_car_given_two_lots_and_one_full() throws NotEnoughPosition {
        Car car = new Car();
        ParkingLot fullLot = new ParkingLot(10,10);
        ParkingLot emptyLot = new ParkingLot(10,0);
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLotManager.assignParkingLot(parkingBoy,fullLot);
        ParkingLotManager.assignParkingLot(parkingBoy,emptyLot);
        parkingBoy.checkLotsManagedForSlot(car);

        assertEquals(1, emptyLot.getParkedCarCount());
    }

    @Test
    void should_return_parked_car_amount_5_when_smart_parking_boy_parks_car_given_two_lots_and_one_amount_parked_cars_are_greater() throws NotEnoughPosition {
        Car car = new Car();
        ParkingLot lot1 = new ParkingLot(10,5);
        ParkingLot lot2 = new ParkingLot(10,4);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLotManager.assignParkingLot(smartParkingBoy,lot1);
        ParkingLotManager.assignParkingLot(smartParkingBoy,lot2);
        smartParkingBoy.smartCheckLotsManagedForSlot(car);

        assertEquals(5, lot2.getParkedCarCount());
    }
    
}
