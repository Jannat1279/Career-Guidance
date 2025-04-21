package com.example.CareerGuidance.CareerGuidance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class FrontController {
	@GetMapping(value= {"/","/front.html"})
	public String displayFront() {
		return "front";
	}
	@GetMapping("/front.html")
	public String showFrontPage(HttpSession session, Model model) {
	    String email = (String) session.getAttribute("userEmail");
	    if (email == null) {
	        return "redirect:/login.html"; // not logged in
	    }
	    model.addAttribute("email", email);
	    return "front.html"; // return front.html
	}


}
