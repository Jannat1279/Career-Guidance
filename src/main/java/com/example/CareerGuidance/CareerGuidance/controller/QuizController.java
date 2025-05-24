package com.example.CareerGuidance.CareerGuidance.controller;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class QuizController {

    // Show the quiz landing page
    @GetMapping("/index")
    public String showQuizPage(Model model) {
        // Replace with real features/questions if needed
        List<String> features = new ArrayList<>();
        for (int i = 1; i <= 59; i++) {
            features.add("Question " + i);
        }

        model.addAttribute("features", features);
        return "index";  // Thymeleaf will load templates/index.html
    }

    // Handle quiz submission
    @PostMapping("/submitQuiz")
    public String submitQuiz(
            @RequestParam("selected") List<Integer> selected,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            Model model
    ) {
        // Convert selected questions to binary list of 59 answers
        List<Integer> answers = new ArrayList<>(Collections.nCopies(59, 0));
        for (Integer index : selected) {
            if (index >= 0 && index < 59) {
                answers.set(index, 1);
            }
        }

        // Here, you can call a prediction service or send to Flask backend
        // For now, just simulate a prediction
        String predictedCareer = "Software Engineer"; // placeholder

        model.addAttribute("career", predictedCareer);
        return "dashboard.html"; // Redirect to result.html (create it!)
    }
}
