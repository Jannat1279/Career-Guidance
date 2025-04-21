package com.example.CareerGuidance.CareerGuidance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.CareerGuidance.CareerGuidance.model.LoginRequest;
import com.example.CareerGuidance.CareerGuidance.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/login.html")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        boolean isValid = userService.validateUser(email, password); // Validate login
        if (isValid) {
            session.setAttribute("userEmail", email); // Set user email in session
            model.addAttribute("successMessage", "Login successful!"); // Add success message to the model

//            return "redirect:/front.html"; // Redirect to front page
            return "login.html";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login.html"; // Redirect to login page if invalid credentials
        }
    }

    // Show login page (GET request)
    @GetMapping("/login.html")
    public String showLoginPage() {
        return "login.html"; // Return login.html view
    }
    
}




