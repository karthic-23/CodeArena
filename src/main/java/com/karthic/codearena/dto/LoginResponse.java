package com.karthic.codearena.dto;

// Response sent after login
public class LoginResponse {

    private String message;
    private String email;

    // Constructor
    public LoginResponse(String message, String email) {
        this.message = message;
        this.email = email;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }
}