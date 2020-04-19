package com.bridgelabz.parkinglot.vehicledetails;

public class VehicleDetails {
    private final String vehicleId;
    private final String vehicleName;

    public VehicleDetails(String vehicleId, String vehicleName) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
