package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static Map<ParkingTicket, Car> parkingTicketMap = new HashMap<>();
    private int capacity = 10;

    public void setSampleCapacity(int capacity) {
        this.capacity = capacity;
    }

    int getCapacity() {
        return capacity;
    }

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
}
