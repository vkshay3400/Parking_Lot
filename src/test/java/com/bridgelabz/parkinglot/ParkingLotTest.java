package com.bridgelabz.parkinglot;

import com.bridgelabz.parkinglot.clientcode.ParkingLot;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void givenOwnersVehicle_WhenParked_ShouldReturnTrue(){
        ParkingLot parkingLot = new ParkingLot();
        boolean isParked = parkingLot.park(new Object());
        Assert.assertTrue(isParked);
    }
}
