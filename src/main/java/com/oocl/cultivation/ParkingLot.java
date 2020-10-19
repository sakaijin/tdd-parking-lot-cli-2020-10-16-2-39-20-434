package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static Map<ParkingTicket, Car> parkingTicketMap = new HashMap<>();
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

    static ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketMap.put(parkingTicket, car);
        return parkingTicket;
    }

    static Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicket {
        return getCar(parkingTicket);
    }

    private static Car getCar(ParkingTicket parkingTicket) throws UnrecognizedParkingTicket {
        if (!parkingTicketMap.containsKey(parkingTicket)){
            throw new UnrecognizedParkingTicket();
        }
        Car car = parkingTicketMap.get(parkingTicket);
        parkingTicketMap.remove(parkingTicket);
        return car;
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
