package com.oocl.cultivation;

import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ConcurrentHashMap<ParkingTicket, CarToBeParked> parkingTicketMap = new ConcurrentHashMap<>();
    private int capacity = 10;
    private int parkedCarCount = 0;

    private int getCapacity() {
        return capacity;
    }

    public int getParkedCarCount() {
        return parkedCarCount;
    }

    void setParkedCarCount(int parkedCarCount) {
        this.parkedCarCount = parkedCarCount;
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

    double getAvailableRatio() {
        return (float) (getCapacity() - getParkedCarCount()) / getCapacity();
    }
}
