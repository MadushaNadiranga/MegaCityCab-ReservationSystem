package com.reservation.model;

import java.time.LocalDateTime;

public class Booking {
    private int bookingId;
    private String customerName;
    private String customerEmail;
    private String nic;
    private String phone;
    private int driverId;
    private int vehicleId;
    private String pickupLocation;
    private String dropoffLocation;
    private LocalDateTime bookingDate;
    private double totalAmount;
    private String paymentStatus;

    // Constructor with bookingId (for retrieving from DB)
    public Booking(int bookingId, String customerName, String customerEmail, String nic, String phone,
                   int driverId, int vehicleId, String pickupLocation, String dropoffLocation,
                   LocalDateTime bookingDate, double totalAmount, String paymentStatus) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.nic = nic;
        this.phone = phone;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    // Constructor without bookingId (for inserting into DB)
    public Booking(String customerName, String customerEmail, String nic, String phone,
                   int driverId, int vehicleId, String pickupLocation, String dropoffLocation,
                   LocalDateTime bookingDate, double totalAmount, String paymentStatus) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.nic = nic;
        this.phone = phone;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getNic() { return nic; }
    public String getPhone() { return phone; }
    public int getDriverId() { return driverId; }
    public int getVehicleId() { return vehicleId; }
    public String getPickupLocation() { return pickupLocation; }
    public String getDropoffLocation() { return dropoffLocation; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }
}
