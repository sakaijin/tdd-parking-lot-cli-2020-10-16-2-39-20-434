package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    void should_return_empty_lot_parked_car_1_when_smart_parking_boy_parks_car_given_two_lots_and_one_full() throws NotEnoughPosition, OperationFail {
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLotManager.assignParkingLot(smartParkingBoy, lot1);
        ParkingLotManager.assignParkingLot(smartParkingBoy, lot2);
        smartParkingBoy.checkLotsManagedForSlot(car1);
        smartParkingBoy.checkLotsManagedForSlot(car2);

        assertEquals(1, lot2.getParkedCarCount());
    }

    @Test
    void should_return_parked_car_amount_5_when_smart_parking_boy_parks_car_given_two_lots_and_one_amount_parked_cars_are_greater() throws NotEnoughPosition, OperationFail {
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot lot1 = new ParkingLot(2);
        ParkingLot lot2 = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLotManager.assignParkingLot(smartParkingBoy, lot1);
        ParkingLotManager.assignParkingLot(smartParkingBoy, lot2);
        smartParkingBoy.smartCheckLotsManagedForSlot(car1);
        smartParkingBoy.smartCheckLotsManagedForSlot(car2);

        assertEquals(1, lot2.getParkedCarCount());
    }
}
