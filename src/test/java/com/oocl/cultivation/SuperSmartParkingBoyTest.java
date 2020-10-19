package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuperSmartParkingBoyTest {
    @Test
    void should_return_empty_lot_parked_car_1_when_super_smart_parking_boy_parks_car_given_two_lots_and_one_full() throws NotEnoughPosition, OperationFail {
        //GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        ParkingLotManager.assignParkingLot(superSmartParkingBoy, lot1);
        ParkingLotManager.assignParkingLot(superSmartParkingBoy, lot2);
        superSmartParkingBoy.checkLotsManagedForSlot(car1);
        //WHEN
        superSmartParkingBoy.checkLotsManagedForSlot(car2);
        //THEN
        assertEquals(1, lot2.getParkedCarCount());
    }

    @Test
    void should_return_parked_car_amount_5_when_super_smart_parking_boy_parks_car_given_two_lots_and_one_amount_parked_cars_are_greater() throws NotEnoughPosition, OperationFail {
        //GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot lot1 = new ParkingLot(2);
        ParkingLot lot2 = new ParkingLot(2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        ParkingLotManager.assignParkingLot(superSmartParkingBoy, lot1);
        ParkingLotManager.assignParkingLot(superSmartParkingBoy, lot2);
        superSmartParkingBoy.smartCheckLotsManagedForSlot(car1);
        //WHEN
        superSmartParkingBoy.smartCheckLotsManagedForSlot(car2);
        //THEN
        assertEquals(1, lot2.getParkedCarCount());
    }

    @Test
    void should_return_parked_car_amount_4_when_super_smart_parking_boy_parks_car_given_two_lots_and_one_amount_parked_cars_ratio_are_greater() throws NotEnoughPosition, OperationFail {
        //GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot lot1 = new ParkingLot(2);
        ParkingLot lot2 = new ParkingLot(2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        ParkingLotManager.assignParkingLot(superSmartParkingBoy, lot1);
        ParkingLotManager.assignParkingLot(superSmartParkingBoy, lot2);
        superSmartParkingBoy.superSmartCheckLotsManagedForSlot(car1);
        //WHEN
        superSmartParkingBoy.superSmartCheckLotsManagedForSlot(car2);
        //THEN
        assertEquals(1, lot2.getParkedCarCount());
    }
}
