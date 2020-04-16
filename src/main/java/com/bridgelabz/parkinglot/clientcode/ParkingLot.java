package com.bridgelabz.parkinglot.clientcode;

public class ParkingLot {
    // VARIABLE
    private Object vehicle;

    // CONSTRUCTOR
    public ParkingLot() {
    }

    // METHOD FOR PARK
    public boolean park(Object vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }

    // METHOD FOR UNPARK
    public boolean unPark(Object vehicle){
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}