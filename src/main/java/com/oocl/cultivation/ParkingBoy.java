package com.oocl.cultivation;

public class ParkingBoy {

    public ParkingBoy(ParkingLot parkingLot) {

    }

    public ParkingTicket park(Car car) {
        return ParkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return null;
    }
}
