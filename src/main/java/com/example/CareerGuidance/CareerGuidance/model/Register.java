package com.example.CareerGuidance.CareerGuidance.model;

import jakarta.persistence.Column;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate ID for each entry
    private Long id;  // Primary key

    private String username;
    private String email;
    private String password;

    // Getters and Setters for the fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Register [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
    }
}


//@Data
//@Entity
//@Table(name="register")
//public class Register {
//
////	@Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the id
////    @Column(name="ID")
////    private Long id;
////	
//	@Id
//	@Column(name="USERNAME")
//    private String username;
//	
//	@Column(name="EMAIL")
//    private String email;
//	
//	@Column(name="PASSWORD")
//    private String password;
//}