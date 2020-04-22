package com.bridgelabz.parkinglot.vehicledetails;

public class VehicleDetails {
    private final String personFitness, vehicleType;
    private final String vehicleName, vehicleColor;
    private final String vehicleId;

    public VehicleDetails(String personFitness, String vehicleType, String vehicleId,
                          String vehicleName, String vehicleColor) {
        this.personFitness = personFitness;
        this.vehicleType = vehicleType;
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.vehicleColor = vehicleColor;
    }

    public String getPersonFitness() {
        return personFitness;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }
}
