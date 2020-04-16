package com.bridgelabz.parkinglot;

import com.bridgelabz.parkinglot.clientcode.ParkingLot;
import com.bridgelabz.parkinglot.clientcode.ParkingLotException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLot = new ParkingLot();
    }

    @Test
    public void givenOwnersVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLot.park(vehicle);
            boolean isParked = parkingLot.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenOwnersVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            parkingLot.park(vehicle);
            parkingLot.park(new Object());
            boolean isParked = parkingLot.isVehicleParked(vehicle);
            Assert.assertEquals(false, isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_FULL, e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenOwnersVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLot.park(vehicle);
            parkingLot.unPark(vehicle);
            boolean isUnparked = parkingLot.isVehicleUnparked(vehicle);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_EMPTY, e.getMessage());
        }
    }
}