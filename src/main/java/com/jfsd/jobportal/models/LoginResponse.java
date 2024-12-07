package com.jfsd.jobportal.models;

public class LoginResponse {

    private String userId;
    private String email;
    private String token;

    // Constructor
    public LoginResponse(int userId, String email, String token) {
        this.userId = String.valueOf(userId);
        this.email = email;
        this.token = token;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
