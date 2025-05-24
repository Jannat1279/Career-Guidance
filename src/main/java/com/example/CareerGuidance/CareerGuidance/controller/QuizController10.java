package com.example.CareerGuidance.CareerGuidance.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

//@RestController
//@RequestMapping("/api")
//public class QuizController10 {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @PostMapping("/predict")
//    public ResponseEntity<?> getPredictionFromFlask(@RequestBody Map<String, Object> quizData) {
//        // Flask server URL (adjust if needed)
//        String flaskUrl = "http://localhost:5001/predict";
//
//        // Prepare headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // Wrap the quiz data in HttpEntity to send
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(quizData, headers);
//
//        try {
//            // Forward POST request to Flask predict endpoint
//            ResponseEntity<Map> response = restTemplate.postForEntity(flaskUrl, requestEntity, Map.class);
//
//            // Return Flask's JSON response directly
//            return ResponseEntity.ok(response.getBody());
//        } catch (Exception e) {
//            // Handle errors like connection refused or JSON parsing
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("error", "Failed to get prediction from Flask: " + e.getMessage()));
//        }
//    }
//}

@Controller
public class QuizController10 {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String flaskBaseUrl = "http://localhost:5001";

    // Serves the static HTML quiz page
    @GetMapping("/get")
    public ResponseEntity<String> getQuizHtml() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("templates/10th_quiz.html");
        String html = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }

    // Proxy to forward /submit-category POST requests to Flask backend
    @PostMapping("/submit-category")
    public ResponseEntity<String> submitCategory(@RequestBody Map<String, Object> payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(flaskBaseUrl + "/submit-category", request, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // Proxy to forward /predict POST request to Flask backend
    @PostMapping("/predict")
    public ResponseEntity<String> predict(@RequestBody Map<String, Object> payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(flaskBaseUrl + "/predict", request, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
