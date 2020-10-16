package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy {
    private ParkingLot parkingLot;
    private List<ParkingLot> assignedLots = new ArrayList<>();

    public SmartParkingBoy() { }

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

    void addLot(ParkingLot lot) {
        this.assignedLots.add(lot);
    }

    public void smartCheckLotsManagedForSlot(Car car) throws NotEnoughPosition {
        this.parkingLot = assignedLots.stream().min(Comparator.comparing(ParkingLot::getParkedCarCount)).orElse(checkLotsManagedForSlot());
        park(car);
    }

    private ParkingLot checkLotsManagedForSlot() {
        for (ParkingLot lot : assignedLots) {
            if (!lot.isCapacityFull()){
                this.parkingLot = lot;
            }
        }
        return parkingLot;
    }
}
