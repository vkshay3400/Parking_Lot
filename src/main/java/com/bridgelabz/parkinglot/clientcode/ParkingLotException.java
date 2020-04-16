package com.bridgelabz.parkinglot.clientcode;

public class ParkingLotException extends Exception {
    private final String message;
    private final ExceptionType type;

    public enum ExceptionType{
        PARKING_LOT_FULL;
    }

    public ParkingLotException(ExceptionType type, String message) {
        this.type = type;
        this.message = message;
    }
}