package com.bridgelabz.parkinglot.exception;

public class ParkingLotException extends Exception {
    public String message;
    public ExceptionType type;

    public enum ExceptionType {
        PARKING_LOT_FULL, NO_VEHICLE;
    }

    public ParkingLotException(ExceptionType type, String message) {
        this.type = type;
        this.message = message;
    }
}
