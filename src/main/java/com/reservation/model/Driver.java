package com.reservation.model;

public class Driver {
    private int driverId;
    private String name;
    private String phone;
    private String licenseNumber;
    private Integer assignedVehicleId;
    private String status;

    public Driver(int driverId, String name, String phone, String licenseNumber, Integer assignedVehicleId, String status) {
        this.driverId = driverId;
        this.name = name;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.assignedVehicleId = assignedVehicleId;
        this.status = status;
    }

    public Driver(String name, String phone, String licenseNumber, Integer assignedVehicleId, String status) {
        this.name = name;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.assignedVehicleId = assignedVehicleId;
        this.status = status;
    }

    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public Integer getAssignedVehicleId() { return assignedVehicleId; }
    public void setAssignedVehicleId(Integer assignedVehicleId) { this.assignedVehicleId = assignedVehicleId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
