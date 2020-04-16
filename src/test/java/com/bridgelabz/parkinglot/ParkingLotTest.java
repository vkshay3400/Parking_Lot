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
        boolean isParked = parkingLot.park(new Object());
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenOwnersVehicle_WhenAlreadyParked_ShouldReturnTrue() {
        parkingLot.park(vehicle);
        boolean isParked = parkingLot.park(new Object());
        Assert.assertFalse(isParked);
    }

    @Test
    public void givenOwnersVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLot.park(vehicle);
        boolean isUnparked = parkingLot.unPark(vehicle);
        Assert.assertTrue(isUnparked);
    }
}
