package com.oocl.cultivation;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if (parkingLot.getCapacity() == 1){
            return null;
        }
        return ParkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return ParkingLot.fetch(parkingTicket);
    }
}
