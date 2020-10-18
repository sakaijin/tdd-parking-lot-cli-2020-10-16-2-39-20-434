package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingJob {
    private ParkingLot parkingLot;
    private List<ParkingLot> assignedLots = new ArrayList<>();

    public ParkingJob(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingJob() { }

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

    public void superSmartCheckLotsManagedForSlot(Car car) throws NotEnoughPosition {
        this.parkingLot = assignedLots.stream().max(Comparator.comparing(ParkingLot::getAvailableRatio)).orElse(null);
        park(car);
    }

    public void smartCheckLotsManagedForSlot(Car car) throws NotEnoughPosition {
        if (Comparator.comparing(ParkingLot::getParkedCarCount).equals(assignedLots.stream())){
            checkLotsManagedForSlot(car);
        }
        this.parkingLot = assignedLots.stream().min(Comparator.comparing(ParkingLot::getParkedCarCount)).orElse(null);
        park(car);
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
