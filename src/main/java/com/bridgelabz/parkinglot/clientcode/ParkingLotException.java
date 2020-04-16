package com.bridgelabz.parkinglot.clientcode;

public class ParkingLotException extends Exception {
    private final String message;
    private final ExceptionType type;

    public enum ExceptionType {
        PARKING_LOT_FULL, PARKING_LOT_EMPTY;
    }

    public ParkingLotException(ExceptionType type, String message) {
        this.type = type;
        this.message = message;
    }
}