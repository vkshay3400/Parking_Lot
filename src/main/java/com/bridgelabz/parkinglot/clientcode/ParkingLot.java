package com.bridgelabz.parkinglot.clientcode;

public class ParkingLot {
    // VARIABLE
    private Object vehicle = null;

    // CONSTRUCTOR
    public ParkingLot() {
    }

    // METHOD FOR PARKING LOT
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "Parking is full");
        this.vehicle = vehicle;
    }

    // METHOD FOR UNPARKING LOT
    public void unPark(Object vehicle) throws ParkingLotException {
        if (this.vehicle == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_EMPTY, "Parking is empty");
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
        }
    }

    // METHOD FOR PARKED VEHICLE
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle)) return true;
        return false;
    }

    // METHOD FOR UNPARKED VEHICLE
    public boolean isVehicleUnparked(Object vehicle) {
        if (this.vehicle != vehicle) return true;
        return false;
    }
}