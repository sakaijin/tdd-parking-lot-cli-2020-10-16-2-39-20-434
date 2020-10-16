package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager {
    public static List<ParkingLot> assignParkingLots(ParkingBoy parkingBoy, ParkingLot lot1, ParkingLot lot2) {
        List<ParkingLot> assignedLots = new ArrayList<>();
        assignedLots.add(lot1);
        assignedLots.add(lot2);
        parkingBoy.managedLots(assignedLots);
        return assignedLots;
    }
}
