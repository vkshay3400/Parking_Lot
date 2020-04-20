package com.bridgelabz.parkinglot;

import com.bridgelabz.parkinglot.clientcode.Attendant;
import com.bridgelabz.parkinglot.clientcode.ParkingLot;
import com.bridgelabz.parkinglot.vehicledetails.VehicleDetails;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.parkingnotification.OwnerNotification;
import com.bridgelabz.parkinglot.parkingnotification.SecurityNotification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

public class ParkingLotTest {
    ParkingLot parkingLot;
    private OwnerNotification owner;
    private SecurityNotification airportSecurity;
    Attendant attendant = new Attendant();

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot();
        owner = new OwnerNotification();
        airportSecurity = new SecurityNotification();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            VehicleDetails vehicleDetails = new VehicleDetails("MH01AB1234", "WagonR");
            attendant.park(vehicleDetails);
            boolean isParked = attendant.isVehicleParked(vehicleDetails);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            VehicleDetails vehicleDetails = new VehicleDetails("MH01AB1234", "WagonR");
            parkingLot.park(vehicleDetails);
            parkingLot.unPark(vehicleDetails);
            boolean isUnparked = attendant.isVehicleUnparked(vehicleDetails);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_VEHICLE, e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            VehicleDetails vehicleDetails = new VehicleDetails("MH01AB1234", "WagonR");
            attendant.park(vehicleDetails);
            boolean isParked = attendant.isVehicleUnparked(vehicleDetails);
            Assert.assertFalse(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_FULL, e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformToOwner() throws ParkingLotException {
        parkingLot.addObserver(owner);
        VehicleDetails vehicleDetails1 = new VehicleDetails("MH01AB1234", "WagonR");
        parkingLot.park(vehicleDetails1);
        VehicleDetails vehicleDetails2 = new VehicleDetails("MH01AB2345", "Santro");
        parkingLot.park(vehicleDetails2);
        Assert.assertEquals("Parking lot is full", owner.getParkingStatus());
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformToAirportSecurity() throws ParkingLotException {
        parkingLot.addObserver(airportSecurity);
        VehicleDetails vehicleDetails1 = new VehicleDetails("MH01AB1234", "WagonR");
        parkingLot.park(vehicleDetails1);
        VehicleDetails vehicleDetails2 = new VehicleDetails("MH01AB2345", "Santro");
        parkingLot.park(vehicleDetails2);
        Assert.assertEquals("Parking lot is full", airportSecurity.getParkingStatus());
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformHaveSpace() throws ParkingLotException {
        parkingLot.addObserver(owner);
        VehicleDetails vehicleDetails1 = new VehicleDetails("MH01AB1234", "WagonR");
        parkingLot.park(vehicleDetails1);
        VehicleDetails vehicleDetails2 = new VehicleDetails("MH01AB2345", "Santro");
        parkingLot.unPark(vehicleDetails2);
        Assert.assertEquals("Parking lot has space", owner.getParkingStatus());
    }

    @Test
    public void givenAVehicle_FindMyCar_FromParkingLot() throws ParkingLotException {
        attendant.addObserver(owner);
        VehicleDetails vehicleDetails1 = new VehicleDetails("MH01AB1234", "WagonR");
        attendant.park(vehicleDetails1);
        VehicleDetails vehicleDetails2 = new VehicleDetails("MH01AB2345", "Santro");
        parkingLot.unPark(vehicleDetails2);
        String result = attendant.getVehiclePosition(vehicleDetails2);
        Assert.assertEquals("MH01AB2345", result);
    }

    @Test
    public void givenAVehicle_ApplyChargesToVehicle_FromParkingLot() throws ParkingLotException {
        attendant.addObserver(owner);
        VehicleDetails vehicleDetails1 = new VehicleDetails("MH01AB1234", "WagonR");
        attendant.park(vehicleDetails1);
        double result = parkingLot.getChargeVehicle(vehicleDetails1);
        Assert.assertEquals(100.0, result, 0.0);
    }
}
