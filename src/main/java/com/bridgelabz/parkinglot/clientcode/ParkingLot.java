package com.bridgelabz.parkinglot.clientcode;

import com.bridgelabz.parkinglot.vehicledetails.VehicleDetails;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.parkingnotification.ParkingLotNotification;

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
}
