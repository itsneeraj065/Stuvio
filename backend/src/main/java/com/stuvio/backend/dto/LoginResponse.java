package com.stuvio.backend.dto;

public class LoginResponse {

    private String message;
    private String email;
    private String token;

    // ✅ ADD THIS
    public String getToken() {
    	return token ;
    }
    public void setToken(String token) {
    	this.token = token ;
    }
    public LoginResponse() {
    }

    public LoginResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}