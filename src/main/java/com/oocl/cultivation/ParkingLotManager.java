package com.oocl.cultivation;

public class ParkingLotManager extends ParkingJob{

    public static void assignParkingLot(ParkingJob parkingBoy, ParkingLot lot) {
        parkingBoy.addLot(lot);
    }

    public static void assignParkingLot(ParkingLotManager parkingLotManager, ParkingLot lot) {
        parkingLotManager.addLot(lot);
    }

    public static ParkingTicket orderPark(ParkingJob parkingBoy, CarToBeParked carToBeParked) throws NotEnoughPosition, OperationFail {
        return parkingBoy.park(carToBeParked);
    }

    public static CarToBeParked orderFetch(ParkingJob parkingBoy, ParkingTicket ticket) throws UnrecognizedParkingTicket, NoTicketException {
        return parkingBoy.fetch(ticket);
    }
}
