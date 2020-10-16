package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager {
    public static void assignParkingLot(ParkingBoy parkingBoy, ParkingLot lot) {
        List<ParkingLot> assignedLots = new ArrayList<>();
        assignedLots.add(lot);
        parkingBoy.managedLots(assignedLots);
    }

    public static void assignParkingLot(SmartParkingBoy parkingBoy, ParkingLot lot) {
        parkingBoy.addLot(lot);
    }
}
