package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static Map<ParkingTicket, CarToBeParked> parkingTicketMap = new HashMap<>();
    private int capacity = 10;
    private int parkedCarCount = 0;

    private int getCapacity() {
        return capacity;
    }

    public int getParkedCarCount() {
        return parkedCarCount;
    }

    public ParkingLot(int lotCapacity) {
        this.capacity = lotCapacity;
    }

    public ParkingLot() { }

    static ParkingTicket park(CarToBeParked carToBeParked) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketMap.put(parkingTicket, carToBeParked);
        return parkingTicket;
    }

    static CarToBeParked fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicket {
        return getCar(parkingTicket);
    }

    private static CarToBeParked getCar(ParkingTicket parkingTicket) throws UnrecognizedParkingTicket {
        if (!parkingTicketMap.containsKey(parkingTicket)){
            throw new UnrecognizedParkingTicket();
        }
        CarToBeParked carToBeParked = parkingTicketMap.get(parkingTicket);
        parkingTicketMap.remove(parkingTicket);
        return carToBeParked;
    }

    boolean isCapacityFull() {
        return parkedCarCount >= capacity;
    }

    void addCarCount() {
        parkedCarCount++;
    }

    double getAvailableRatio() {
        return (float) (getCapacity() - getParkedCarCount()) / getCapacity();
    }
}
