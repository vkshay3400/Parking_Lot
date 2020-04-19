package com.bridgelabz.parkinglot.parkingnotification;

public class SecurityNotification implements ParkingLotNotification {

    private String parkingStatus;

    @Override
    public void update(String message) {
        this.setParkingStatus((String) message);
    }

    public String getParkingStatus() {
        return parkingStatus;
    }

    public void setParkingStatus(String isFull) {
        this.parkingStatus = isFull;
    }
}
