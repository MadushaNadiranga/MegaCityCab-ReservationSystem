package com.reservation.model;

public class User {
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String role;
    private String profilePicture;

    public User(String fullName, String username, String email, String password, String phone, String role, String profilePicture) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.profilePicture = profilePicture;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
