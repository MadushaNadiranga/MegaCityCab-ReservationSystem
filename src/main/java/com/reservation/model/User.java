package com.reservation.model;

public class User {
    private String fullName;
    private String username;
    private String email;
    private String passwordHash;
    private String phone;
    private String role;
    private String profilePicture;

    // Constructor
    public User(String fullName, String username, String email, String passwordHash, String phone, String role, String profilePicture) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.role = role;
        this.profilePicture = profilePicture;
    }

    // Getters
    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
