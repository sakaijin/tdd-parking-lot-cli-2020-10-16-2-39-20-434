package com.oocl.cultivation;

public class ParkingLotManager extends ParkingJobTasks {

    public static void assignParkingLot(ParkingJobTasks parkingBoy, ParkingLot lot) {
        parkingBoy.addLot(lot);
    }

    public static void assignParkingLot(SmartParkingBoy smartParkingBoy, ParkingLot lot) {
        smartParkingBoy.addLot(lot);
    }

    public static void assignParkingLot(SuperSmartParkingBoy superSmartParkingBoy, ParkingLot lot) {
        superSmartParkingBoy.addLot(lot);
    }

    public static void assignParkingLot(ParkingLotManager parkingLotManager, ParkingLot lot) {
        parkingLotManager.addLot(lot);
    }

    public static ParkingTicket orderPark(ParkingJobTasks parkingBoy, Car car) throws NotEnoughPosition, OperationFail {
        return parkingBoy.park(car);
    }

    public static Car orderFetch(ParkingJobTasks parkingBoy, ParkingTicket ticket) throws UnrecognizedParkingTicket, NoTicketException {
        return parkingBoy.fetch(ticket);
    }
}
