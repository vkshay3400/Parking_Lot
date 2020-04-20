package com.bridgelabz.parkinglot.clientcode;

import com.bridgelabz.parkinglot.vehicledetails.VehicleDetails;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.parkingnotification.ParkingLotNotification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Attendant {

    // VARIABLE
    private VehicleDetails vehicleDetails;
    private int parkingCapacity = 2;

    // MAP AND LIST
    HashMap<String, VehicleDetails> vehicleHashMap = new HashMap<>();
    List<ParkingLotNotification> notificationList = new ArrayList<>();

    // METHOD FOR PARKING LOT
    public void park(VehicleDetails vehicleDetails) throws ParkingLotException {
        if (parkingCapacity > vehicleHashMap.size())
            vehicleHashMap.put(vehicleDetails.getVehicleId(), vehicleDetails);
        else
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "Parking lot is full");
        if (parkingCapacity == vehicleHashMap.size())
            setParkingLotStatus("Parking lot is full");
    }

    // METHOD FOR UNPARKING LOT
    public void unPark(VehicleDetails vehicleDetails) throws ParkingLotException {
        if (vehicleDetails == null && !this.vehicleDetails.equals(vehicleDetails))
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_VEHICLE, "No vehicle in parking lot");
        else if (vehicleHashMap.containsKey(vehicleDetails.getVehicleId()))
            vehicleHashMap.remove(vehicleDetails.getVehicleId());
        if (parkingCapacity > vehicleHashMap.size())
            setParkingLotStatus("Parking lot has space");
    }

    // METHOD FOR PARKED VEHICLE
    public boolean isVehicleParked(VehicleDetails vehicleDetails) {
        if (vehicleHashMap.containsKey(vehicleDetails.getVehicleId())) return true;
        return false;
    }

    // METHOD FOR UNPARKED VEHICLE
    public boolean isVehicleUnparked(VehicleDetails vehicleDetails) {
        if (!vehicleHashMap.containsKey(vehicleDetails.getVehicleId())) return true;
        return false;
    }

    // METHOD TO ADD
    public void addObserver(ParkingLotNotification lotNotification) {
        notificationList.add(lotNotification);
    }

    // METHOD TO GET STATUS
    public void setParkingLotStatus(String message) {
        for (ParkingLotNotification notification : notificationList) {
            notification.update(message);
        }
    }
}
