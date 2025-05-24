package com.example.CareerGuidance.CareerGuidance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/career")
public class CareerController {

    @PostMapping("/predict")
    public ResponseEntity<String> predictCareer(@RequestBody List<Integer> userAnswers) {
        if (userAnswers.size() != 59) {
            return ResponseEntity.badRequest().body("Invalid number of answers");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5000/predict";

        Map<String, Object> request = new HashMap<>();
        request.put("answers", userAnswers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            String career = (String) response.getBody().get("career");
            return ResponseEntity.ok(career);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
