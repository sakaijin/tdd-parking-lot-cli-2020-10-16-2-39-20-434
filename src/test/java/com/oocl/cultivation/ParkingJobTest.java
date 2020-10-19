package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingJobTest {
    @Test
    void should_return_parking_ticket_when_parking_boy_park_car_given_car() throws NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked = new CarToBeParked();
        ParkingJob parkingBoy = new ParkingJob(new ParkingLot());

        ParkingTicket parkingTicket = parkingBoy.park(carToBeParked);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_right_car_ticket_when_parking_boy_fetch_car_given_ticket() throws NoTicketException, UnrecognizedParkingTicket, NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked = new CarToBeParked();
        ParkingJob parkingBoy = new ParkingJob(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(carToBeParked);

        CarToBeParked fetchedCarToBeParked = parkingBoy.fetch(parkingTicket);

        assertSame(carToBeParked, fetchedCarToBeParked);
    }

    @Test
    void should_return_right_cars_when_parking_boy_fetch_cars_given_tickets() throws NoTicketException, UnrecognizedParkingTicket, NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked1 = new CarToBeParked();
        CarToBeParked carToBeParked2 = new CarToBeParked();
        ParkingJob parkingBoy = new ParkingJob(new ParkingLot());
        ParkingTicket parkingTicket1 = parkingBoy.park(carToBeParked1);
        ParkingTicket parkingTicket2 = parkingBoy.park(carToBeParked2);

        CarToBeParked fetchedCarToBeParked1 = parkingBoy.fetch(parkingTicket1);
        CarToBeParked fetchedCarToBeParked2 = parkingBoy.fetch(parkingTicket2);

        assertSame(carToBeParked1, fetchedCarToBeParked1);
        assertSame(carToBeParked2, fetchedCarToBeParked2);
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_parking_boy_fetch_cars_given_wrong_ticket() throws NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked = new CarToBeParked();
        ParkingJob parkingBoy = new ParkingJob(new ParkingLot());

        parkingBoy.park(carToBeParked);
        ParkingTicket wrongTicket = new ParkingTicket();

        assertThrows(UnrecognizedParkingTicket.class, () -> parkingBoy.fetch(wrongTicket));
    }

    @Test
    void should_return_no_ticket_exception__when_parking_boy_fetch_car_given_no_ticket() throws NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked = new CarToBeParked();
        ParkingJob parkingBoy = new ParkingJob(new ParkingLot());
        parkingBoy.park(carToBeParked);

        assertThrows(NoTicketException.class, () -> parkingBoy.fetch(null));
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_parking_boy_fetch_car_given_used_ticket() throws NoTicketException, UnrecognizedParkingTicket, NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked = new CarToBeParked();
        ParkingJob parkingBoy = new ParkingJob(new ParkingLot());
        ParkingTicket usedTicket = parkingBoy.park(carToBeParked);

        parkingBoy.fetch(usedTicket);

        assertThrows(UnrecognizedParkingTicket.class, () -> parkingBoy.fetch(usedTicket));
    }

    @Test
    void should_return_no_parking_ticket_when_parking_boy_parks_car_given_parking_lot_capacity_full() throws NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked1 = new CarToBeParked();
        CarToBeParked carToBeParked2 = new CarToBeParked();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingJob parkingBoy = new ParkingJob(parkingLot);
        parkingBoy.park(carToBeParked1);

        assertThrows(NotEnoughPosition.class, () -> parkingBoy.park(carToBeParked2));
    }

    @Test
    void should_return_empty_lot_parked_car_1__when_parking_boy_parks_car_given_two_lots_and_one_full() throws NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked1 = new CarToBeParked();
        CarToBeParked carToBeParked2 = new CarToBeParked();
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        ParkingJob parkingBoy = new ParkingJob();
        ParkingLotManager.assignParkingLot(parkingBoy,lot1);
        ParkingLotManager.assignParkingLot(parkingBoy,lot2);
        parkingBoy.checkLotsManagedForSlot(carToBeParked1);
        parkingBoy.checkLotsManagedForSlot(carToBeParked2);

        assertEquals(1, lot2.getParkedCarCount());
    }

    @Test
    void should_return_parked_car_amount_5_when_smart_parking_boy_parks_car_given_two_lots_and_one_amount_parked_cars_are_greater() throws NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked1 = new CarToBeParked();
        CarToBeParked carToBeParked2 = new CarToBeParked();
        ParkingLot lot1 = new ParkingLot(2);
        ParkingLot lot2 = new ParkingLot(2);
        ParkingJob smartParkingBoy = new ParkingJob();
        ParkingLotManager.assignParkingLot(smartParkingBoy,lot1);
        ParkingLotManager.assignParkingLot(smartParkingBoy,lot2);
        smartParkingBoy.smartCheckLotsManagedForSlot(carToBeParked1);
        smartParkingBoy.smartCheckLotsManagedForSlot(carToBeParked2);

        assertEquals(1, lot2.getParkedCarCount());
    }

    @Test
    void should_return_parked_car_amount_4_when_smart_parking_boy_parks_car_given_two_lots_and_one_amount_parked_cars_ratio_are_greater() throws NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked1 = new CarToBeParked();
        CarToBeParked carToBeParked2 = new CarToBeParked();
        ParkingLot lot1 = new ParkingLot(2);
        ParkingLot lot2 = new ParkingLot(2);
        ParkingJob superSmartParkingBoy = new ParkingJob();
        ParkingLotManager.assignParkingLot(superSmartParkingBoy,lot1);
        ParkingLotManager.assignParkingLot(superSmartParkingBoy,lot2);
        superSmartParkingBoy.superSmartCheckLotsManagedForSlot(carToBeParked1);
        superSmartParkingBoy.superSmartCheckLotsManagedForSlot(carToBeParked2);

        assertEquals(1, lot2.getParkedCarCount());
    }

    @Test
    void should_return_parked_car_when_parking_boy_parks_car_given_two_parking_boy_manages_same_lot() throws NotEnoughPosition, UnrecognizedParkingTicket, NoTicketException, OperationFail {
        CarToBeParked carToBeParked = new CarToBeParked();
        ParkingLot lot = new ParkingLot(2);
        ParkingJob parkingBoy1 = new ParkingJob(lot);
        ParkingJob parkingBoy2 = new ParkingJob(lot);
        ParkingLotManager.assignParkingLot(parkingBoy1,lot);
        ParkingLotManager.assignParkingLot(parkingBoy2,lot);

        ParkingTicket ticket = ParkingLotManager.orderPark(parkingBoy1, carToBeParked);
        CarToBeParked orderedCarToBeParked = ParkingLotManager.orderFetch(parkingBoy1, ticket);

        assertSame(carToBeParked, orderedCarToBeParked);
    }

    @Test
    void should_return_parked_car_amount_1_when_parking_lot_manager_parks_car_given_two_lots_and_one_full() throws NotEnoughPosition, OperationFail {
        CarToBeParked carToBeParked1 = new CarToBeParked();
        CarToBeParked carToBeParked2 = new CarToBeParked();
        ParkingLot fullLot = new ParkingLot(1);
        ParkingLot emptyLot = new ParkingLot(1);
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        ParkingLotManager.assignParkingLot(parkingLotManager,fullLot);
        ParkingLotManager.assignParkingLot(parkingLotManager,emptyLot);
        parkingLotManager.checkLotsManagedForSlot(carToBeParked1);
        parkingLotManager.checkLotsManagedForSlot(carToBeParked2);

        assertEquals(1, emptyLot.getParkedCarCount());
    }

    @Test
    void should_return_error_when_parking_boy_parks__car_given_no_car() {
        ParkingJob parkingBoy = new ParkingJob(new ParkingLot());

        assertThrows(OperationFail.class, () -> parkingBoy.park(null));
    }
}
