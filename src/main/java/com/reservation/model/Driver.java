package com.reservation.model;

public class Driver {
    private int driverId;
    private String name;
    private String phone;
    private String licenseNumber;
    private Integer assignedVehicleId;
    private String status;

    public Driver(String name, String phone, String licenseNumber, Integer assignedVehicleId, String status) {
        this.name = name;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.assignedVehicleId = assignedVehicleId;
        this.status = status;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Integer getAssignedVehicleId() {
        return assignedVehicleId;
    }

    public String getStatus() {
        return status;
    }
}