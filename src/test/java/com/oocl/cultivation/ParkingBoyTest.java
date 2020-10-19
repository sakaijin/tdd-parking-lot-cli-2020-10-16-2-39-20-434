package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_parking_boy_park_car_given_car() throws NotEnoughPosition, OperationFail {
        //GIVEN
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        //WHEN
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //THEN
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_right_car_ticket_when_parking_boy_fetch_car_given_ticket() throws NoTicketException, UnrecognizedParkingTicket, NotEnoughPosition, OperationFail {
        //GIVEN
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //WHEN
        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        //THEN
        assertSame(car, fetchedCar);
    }

    @Test
    void should_return_right_cars_when_parking_boy_fetch_cars_given_tickets() throws NoTicketException, UnrecognizedParkingTicket, NotEnoughPosition, OperationFail {
        //GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        //WHEN
        Car fetchedCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);
        //THEN
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_parking_boy_fetch_cars_given_wrong_ticket() throws NotEnoughPosition, OperationFail {
        //GIVEN
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        parkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();
        //WHEN...THEN
        assertThrows(UnrecognizedParkingTicket.class, () -> parkingBoy.fetch(wrongTicket));
    }

    @Test
    void should_return_no_ticket_exception__when_parking_boy_fetch_car_given_no_ticket() throws NotEnoughPosition, OperationFail {
        //GIVEN
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        parkingBoy.park(car);
        //WHEN...THEN
        assertThrows(NoTicketException.class, () -> parkingBoy.fetch(null));
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_parking_boy_fetch_car_given_used_ticket() throws NoTicketException, UnrecognizedParkingTicket, NotEnoughPosition, OperationFail {
        //GIVEN
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket usedTicket = parkingBoy.park(car);
        parkingBoy.fetch(usedTicket);
        //WHEN...THEN
        assertThrows(UnrecognizedParkingTicket.class, () -> parkingBoy.fetch(usedTicket));
    }

    @Test
    void should_return_no_parking_ticket_when_parking_boy_parks_car_given_parking_lot_capacity_full() throws NotEnoughPosition, OperationFail {
        //GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(car1);
        //WHEN...THEN
        assertThrows(NotEnoughPosition.class, () -> parkingBoy.park(car2));
    }

    @Test
    void should_return_empty_lot_parked_car_1__when_parking_boy_parks_car_given_two_lots_and_one_full() throws NotEnoughPosition, OperationFail {
        //GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLotManager.assignParkingLot(parkingBoy,lot1);
        ParkingLotManager.assignParkingLot(parkingBoy,lot2);
        parkingBoy.checkLotsManagedForSlot(car1);
        //WHEN
        parkingBoy.checkLotsManagedForSlot(car2);
        //WHEN
        assertEquals(1, lot2.getParkedCarCount());
    }

    @Test
    void should_return_parked_car_when_parking_boy_parks_car_given_two_parking_boy_manages_same_lot() throws NotEnoughPosition, UnrecognizedParkingTicket, NoTicketException, OperationFail {
        //GIVEN
        Car car = new Car();
        ParkingLot lot = new ParkingLot(2);
        ParkingBoy parkingBoy1 = new ParkingBoy(lot);
        ParkingBoy parkingBoy2 = new ParkingBoy(lot);
        ParkingLotManager.assignParkingLot(parkingBoy1,lot);
        ParkingLotManager.assignParkingLot(parkingBoy2,lot);
        //WHEN
        ParkingTicket ticket = ParkingLotManager.orderPark(parkingBoy1, car);
        Car orderedCar = ParkingLotManager.orderFetch(parkingBoy1, ticket);
        //THEN
        assertSame(car, orderedCar);
    }

    @Test
    void should_return_error_when_parking_boy_parks__car_given_no_car() {
        //GIVEN
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        //WHEN...THEN
        assertThrows(OperationFail.class, () -> parkingBoy.park(null));
    }
}
