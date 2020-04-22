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
            attendant.getParkingCapacity(2);
            VehicleDetails vehicleDetails1 = new VehicleDetails("HANDICAP",
                    "SmallCar", "MH01AB1234", "WagonR", "White");
            attendant.park(vehicleDetails1);
            VehicleDetails vehicleDetails2 = new VehicleDetails("HANDICAP",
                    "SmallCar", "MH01AB2345", "WagonR", "White");
            attendant.park(vehicleDetails2);
            boolean isParked = attendant.isVehicleParked(vehicleDetails1);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            attendant.getParkingCapacity(2);
            VehicleDetails vehicleDetails1 = new VehicleDetails("HANDICAP",
                    "SmallCar", "MH01AB1234", "WagonR", "White");
            attendant.park(vehicleDetails1);
            attendant.unPark(vehicleDetails1);
            boolean isUnparked = attendant.isVehicleUnparked(vehicleDetails1);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_VEHICLE, e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            attendant.getParkingCapacity(2);
            VehicleDetails vehicleDetails1 = new VehicleDetails("HANDICAP",
                    "SmallCar", "MH01AB1234", "WagonR", "White");
            attendant.park(vehicleDetails1);
            boolean isParked = attendant.isVehicleUnparked(vehicleDetails1);
            Assert.assertFalse(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_FULL, e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformToOwner() throws ParkingLotException {
        attendant.getParkingCapacity(2);
        parkingLot.addObserver(owner);
        VehicleDetails vehicleDetails1 = new VehicleDetails("HANDICAP",
                "SmallCar", "MH01AB1234", "WagonR", "White");
        attendant.park(vehicleDetails1);
        VehicleDetails vehicleDetails2 = new VehicleDetails("HANDICAP",
                "SmallCar", "MH01AB2345", "WagonR", "White");
        attendant.park(vehicleDetails2);
        Assert.assertEquals("Parking lot is full", owner.getParkingStatus());
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformToAirportSecurity() throws ParkingLotException {
        attendant.getParkingCapacity(2);
        parkingLot.addObserver(airportSecurity);
        VehicleDetails vehicleDetails1 = new VehicleDetails("HANDICAP",
                "SmallCar", "MH01AB1234", "WagonR", "White");
        attendant.park(vehicleDetails1);
        VehicleDetails vehicleDetails2 = new VehicleDetails("HANDICAP",
                "SmallCar", "MH01AB2345", "WagonR", "White");
        attendant.park(vehicleDetails2);
        Assert.assertEquals("Parking lot is full", airportSecurity.getParkingStatus());
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformHaveSpace() throws ParkingLotException {
        attendant.getParkingCapacity(3);
        parkingLot.addObserver(owner);
        VehicleDetails vehicleDetails1 = new VehicleDetails("HANDICAP",
                "SmallCar", "MH01AB1234", "WagonR", "White");
        attendant.park(vehicleDetails1);
        VehicleDetails vehicleDetails2 = new VehicleDetails("HANDICAP",
                "SmallCar", "MH01AB2345", "WagonR", "White");
        attendant.park(vehicleDetails2);
        Assert.assertEquals("Parking lot has space", owner.getParkingStatus());
    }

    @Test
    public void givenAVehicle_FindMyCar_FromParkingLot() throws ParkingLotException {
        attendant.getParkingCapacity(2);
        VehicleDetails vehicleDetails1 = new VehicleDetails("HANDICAP",
                "SmallCar", "MH01AB1234", "WagonR", "White");
        attendant.park(vehicleDetails1);
        VehicleDetails vehicleDetails2 = new VehicleDetails("HANDICAP",
                "SmallCar", "MH01AB2345", "WagonR", "White");
        attendant.park(vehicleDetails2);
        String result = attendant.getVehiclePosition(vehicleDetails2);
        Assert.assertEquals("MH01AB2345", result);
    }

    @Test
    public void givenAVehicle_ApplyChargesToVehicle_FromParkingLot() throws ParkingLotException {
        attendant.getParkingCapacity(2);
        attendant.addObserver(owner);
        VehicleDetails vehicleDetails1 = new VehicleDetails("HANDICAP",
                "SmallCar", "MH01AB1234", "WagonR", "White");
        attendant.park(vehicleDetails1);
        double result = parkingLot.getChargeVehicle(vehicleDetails1);
        Assert.assertEquals(100.0, result, 0.0);
    }

    @Test
    public void givenAVehicle_EvenlyPark_ShouldReturnTrue() {
        try {
            attendant.getParkingCapacity(5);
            VehicleDetails vehicleDetails1 = new VehicleDetails("HANDICAP",
                    "SmallCar", "MH01AB1234", "WagonR", "White");
            attendant.park(vehicleDetails1);
            VehicleDetails vehicleDetails2 = new VehicleDetails("HANDICAP",
                    "SmallCar", "MH01AB2345", "WagonR", "White");
            attendant.park(vehicleDetails2);
            VehicleDetails vehicleDetails4 = new VehicleDetails("HANDICAP",
                    "SmallCar", "MH01AB35456", "WagonR", "White");
            attendant.park(vehicleDetails4);
            boolean isParked = attendant.isVehicleParked(vehicleDetails2);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

}
