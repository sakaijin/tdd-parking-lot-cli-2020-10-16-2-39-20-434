package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    private ParkingLot parkingLot;
    private List<ParkingLot> assignedLots;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingBoy() { }

    public ParkingTicket park(Car car) throws NotEnoughPosition {
        if (parkingLot.isCapacityFull()){
            throw new NotEnoughPosition();
        }
        parkingLot.addCarCount();
        return ParkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) throws NoTicketException, UnrecognizedParkingTicket {
        if (parkingTicket == null){
            throw new NoTicketException();
        }
        return ParkingLot.fetch(parkingTicket);
    }

    void managedLots(List<ParkingLot> assignedLots) {
        this.assignedLots = assignedLots;
    }

    public void checkLotsManagedForSlot(Car car) throws NotEnoughPosition {
        for (ParkingLot lot : assignedLots) {
            if (!lot.isCapacityFull()){
                parkingLot = lot;
                park(car);
            }
        }
    }
}
