package com.example.CareerGuidance.CareerGuidance.model;

import lombok.Data;

@Data
public class LoginRequest {
	
	    private String email;
	    private String password;
	   
	    public LoginRequest() {}

	    // Getters
	    public String getEmail() {
	        return email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    // Setters
	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}


