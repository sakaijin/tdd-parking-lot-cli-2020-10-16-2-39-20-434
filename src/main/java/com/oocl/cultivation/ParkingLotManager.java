package com.oocl.cultivation;

public class ParkingLotManager extends ParkingJob{
    public ParkingLotManager() { }

    public static void assignParkingLot(ParkingJob parkingBoy, ParkingLot lot) {
        parkingBoy.addLot(lot);
    }

    public static void assignParkingLot(ParkingLotManager parkingLotManager, ParkingLot lot) {
        parkingLotManager.addLot(lot);
    }

    public static ParkingTicket orderPark(ParkingJob parkingBoy, Car car) throws NotEnoughPosition {
        return parkingBoy.park(car);
    }

    public static Car orderFetch(ParkingJob parkingBoy, ParkingTicket ticket) throws UnrecognizedParkingTicket, NoTicketException {
        return parkingBoy.fetch(ticket);
    }
}
