package com.institute.portal.user_service.dto;

public class UpdateUserRequest {
    private String fullName;

    // optional: add password field if you want to allow password change
    private String password;

    // Getters & setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
