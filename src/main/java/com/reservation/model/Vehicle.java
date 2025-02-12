package com.reservation.model;

public class Vehicle {
    private int vehicleId;
    private String model;
    private int year;
    private String licensePlate;
    private String status;
    private String vehicleImage;

    public Vehicle(String model, int year, String licensePlate, String status, String vehicleImage) {
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.status = status;
        this.vehicleImage = vehicleImage;
    }

    public Vehicle(int vehicleId, String model, int year, String licensePlate, String status, String vehicleImage) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.status = status;
        this.vehicleImage = vehicleImage;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getStatus() {
        return status;
    }

    public String getVehicleImage() {
        return vehicleImage;
    }
}