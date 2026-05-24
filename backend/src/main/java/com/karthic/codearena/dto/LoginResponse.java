package com.karthic.codearena.dto;

// Response sent after login
public class LoginResponse {

    private String message;
    private String token; // 🔥 replace email with token

    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}