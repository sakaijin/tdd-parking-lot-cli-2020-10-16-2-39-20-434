package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static Map<ParkingTicket, Car> parkingTicketMap = new HashMap<>();

    static ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketMap.put(parkingTicket, car);
        return parkingTicket;
    }

    static Car fetch(ParkingTicket parkingTicket) {
        return parkingTicketMap.get(parkingTicket);
    }
}
