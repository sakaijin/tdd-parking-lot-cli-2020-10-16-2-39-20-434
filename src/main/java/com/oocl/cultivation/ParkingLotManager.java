package com.oocl.cultivation;

public class ParkingLotManager {
    public static void assignParkingLot(ParkingBoy parkingBoy, ParkingLot lot) {
        parkingBoy.addLot(lot);
    }

    public static void assignParkingLot(SmartParkingBoy parkingBoy, ParkingLot lot) {
        parkingBoy.addLot(lot);
    }
}
