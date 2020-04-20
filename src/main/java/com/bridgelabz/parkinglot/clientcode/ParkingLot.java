package com.bridgelabz.parkinglot.clientcode;

import com.bridgelabz.parkinglot.vehicledetails.VehicleDetails;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.parkingnotification.ParkingLotNotification;

import java.time.Duration;
import java.time.Instant;

public class ParkingLot {
    Attendant attendant = new Attendant();

    // METHOD FOR PARKING LOT
    public void park(VehicleDetails vehicleDetails) throws ParkingLotException {
        attendant.park(vehicleDetails);
    }

    // METHOD FOR UNPARKING LOT
    public void unPark(VehicleDetails vehicleDetails) throws ParkingLotException {
        attendant.unPark(vehicleDetails);
    }

    // METHOD TO ADD
    public void addObserver(ParkingLotNotification lotNotification) {
        attendant.addObserver(lotNotification);
    }

    // METHOD TO CHARGE A VEHICLE
    public double getChargeVehicle(VehicleDetails vehicleDetails) {
        attendant.getVehiclePosition(vehicleDetails);
        Duration duration = Duration.between(Instant.now(), Instant.now().plus(Duration.ofMinutes(10)));
        double charge = (duration.toMinutes()) * 10;
        return charge;
    }
}
