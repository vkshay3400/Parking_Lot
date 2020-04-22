package com.bridgelabz.parkinglot.clientcode;

import com.bridgelabz.parkinglot.constants.Types;
import com.bridgelabz.parkinglot.vehicledetails.VehicleDetails;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.parkingnotification.ParkingLotNotification;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Attendant {

    // VARIABLE
    private VehicleDetails vehicleDetails;
    public int parkingCapacity;
    Types driverType;
    String parkingLot[] = new String[5];

    // MAP AND LIST
    HashMap<String, VehicleDetails> vehicleHashMap = new HashMap<>();
    List<ParkingLotNotification> notificationList = new ArrayList<>();

    // METHOD TO GET PARKING LOT CAPACITY
    public int getParkingCapacity(int parkingCapacityValue) {
        if (parkingCapacityValue >= 3)
            return parkingCapacity = parkingCapacityValue;
        return parkingCapacity = 2;
    }

    // METHOD FOR PARKING LOT
    public void park(VehicleDetails vehicleDetails) throws ParkingLotException {
        if (this.parkingCapacity > vehicleHashMap.size())
            getParkingStatusCheck(vehicleDetails);
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

    // METHOD TO GET STATUS TO OBSERVER
    public void setParkingLotStatus(String message) {
        for (ParkingLotNotification notification : notificationList) {
            notification.update(message);
        }
    }

    // METHOD TO FIND CAR
    public VehicleDetails searchCar() {
        isVehicleParked(vehicleDetails);
        return vehicleHashMap.get(vehicleDetails.getVehicleId());
    }

    // METHOD TO GET VEHICLE POSITION
    public String getVehiclePosition(VehicleDetails vehicleDetails) {
        vehicleHashMap.keySet().stream()
                .filter(key -> vehicleDetails.equals(vehicleHashMap.get(key)))
                .map(key -> vehicleDetails.getVehicleId())
                .collect(Collectors.toSet());
        return vehicleDetails.getVehicleId();
    }

    // TO CHECK PARKING STATUS
    public Serializable getParkingStatusCheck(VehicleDetails vehicleDetails) throws ParkingLotException {
        vehicleHashMap.putIfAbsent(vehicleDetails.getVehicleId(), vehicleDetails);
        Set<String> keySet = vehicleHashMap.keySet();
        ArrayList<String> arrayList = new ArrayList<String>(keySet);
        Iterator<String> lotNumber = vehicleHashMap.keySet().iterator();
        int index = 0;
        while (lotNumber.hasNext()) {
            if (vehicleDetails.getPersonFitness().equals(driverType.HANDICAP) && (lotNumber == null)) {
                index--;
                index--;
                String lot = lotNumber.next();
                parkingLot[index] = lot;
            }
            if (index % 2 == 0) {
                index++;
                String lot = lotNumber.next();
                parkingLot[index] = lot;
                break;
            }
            if (index % 2 != 0) {
                index++;
                String lot = lotNumber.next();
                parkingLot[index] = lot;
                break;
            }
            index++;
        }
        return parkingLot[index];
    }
}
