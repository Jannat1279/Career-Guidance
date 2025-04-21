package com.example.CareerGuidance.CareerGuidance.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CareerGuidance.CareerGuidance.model.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CareerGuidance.CareerGuidance.repository.RegisterRepository;

@Service
public class UserService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean validateUser(String email, String password) {
        Optional<Register> userOpt = registerRepository.findByEmail(email);
        
        if (userOpt.isPresent()) {
            Register user = userOpt.get();
            // Use BCryptPasswordEncoder to compare the password hashes
            System.out.println("User found: " + user.getEmail()); // Add log here
            boolean isPasswordValid = passwordEncoder.matches(password, user.getPassword());
            System.out.println("Password valid: " + isPasswordValid); // Log password check result
            return isPasswordValid;
        }
        return false;
    }
            

    public Register getUserByEmail(String email) {
        return registerRepository.findByEmail(email).orElse(null);
    }
}
