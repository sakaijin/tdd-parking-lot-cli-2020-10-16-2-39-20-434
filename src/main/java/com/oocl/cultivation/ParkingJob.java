package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingJob {
    private ParkingLot parkingLot;
    private List<ParkingLot> assignedLots = new ArrayList<>();

    public ParkingJob(ParkingLot managedLot) {
        this.parkingLot = managedLot;
    }

    public ParkingJob() { }

    public ParkingTicket park(CarToBeParked carToBeParked) throws NotEnoughPosition, OperationFail {
        if (carToBeParked == null){
            throw new OperationFail();
        }
        if (parkingLot.isCapacityFull()){
            throw new NotEnoughPosition();
        }
        parkingLot.addCarCount();
        return ParkingLot.park(carToBeParked);
    }

    public CarToBeParked fetch(ParkingTicket parkingTicket) throws NoTicketException, UnrecognizedParkingTicket {
        if (parkingTicket == null){
            throw new NoTicketException();
        }
        return ParkingLot.fetch(parkingTicket);
    }

    void addLot(ParkingLot lot) {
        this.assignedLots.add(lot);
    }

    public void superSmartCheckLotsManagedForSlot(CarToBeParked carToBeParked) throws NotEnoughPosition, OperationFail {
        this.parkingLot = assignedLots.stream().max(Comparator.comparing(ParkingLot::getAvailableRatio)).orElse(null);
        park(carToBeParked);
    }

    public void smartCheckLotsManagedForSlot(CarToBeParked carToBeParked) throws NotEnoughPosition, OperationFail {
        if (Comparator.comparing(ParkingLot::getParkedCarCount).equals(assignedLots.stream())){
            checkLotsManagedForSlot(carToBeParked);
        }
        this.parkingLot = assignedLots.stream().min(Comparator.comparing(ParkingLot::getParkedCarCount)).orElse(null);
        park(carToBeParked);
    }

    public void checkLotsManagedForSlot(CarToBeParked carToBeParked) throws NotEnoughPosition, OperationFail {
        for (ParkingLot lot : assignedLots) {
            if (!lot.isCapacityFull()){
                parkingLot = lot;
                park(carToBeParked);
            }
        }
    }
}
