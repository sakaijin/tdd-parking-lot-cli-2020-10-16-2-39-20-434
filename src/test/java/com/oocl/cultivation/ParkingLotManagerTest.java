package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotManagerTest {
    @Test
    void should_return_parked_car_amount_1_when_parking_lot_manager_parks_car_given_two_lots_and_one_full() throws NotEnoughPosition, OperationFail {
        //GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot fullLot = new ParkingLot(1);
        ParkingLot emptyLot = new ParkingLot(1);
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        ParkingLotManager.assignParkingLot(parkingLotManager, fullLot);
        ParkingLotManager.assignParkingLot(parkingLotManager, emptyLot);
        //WHEN
        parkingLotManager.checkLotsManagedForSlot(car1);
        parkingLotManager.checkLotsManagedForSlot(car2);
        //THEN
        assertEquals(1, emptyLot.getParkedCarCount());
    }
}
