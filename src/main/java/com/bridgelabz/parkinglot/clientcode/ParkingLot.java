package com.bridgelabz.parkinglot.clientcode;

public class ParkingLot {
    // VARIABLE
    private Object vehicle;

    // CONSTRUCTOR
    public ParkingLot() {
    }

    // METHOD FOR PARK
    public boolean park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL,"Parking is full");
        this.vehicle = vehicle;
        return false;
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