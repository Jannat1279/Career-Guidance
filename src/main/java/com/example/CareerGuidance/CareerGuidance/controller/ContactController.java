package com.example.CareerGuidance.CareerGuidance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

	@GetMapping(value={"/contact"})
	public String displayRegister() {
		return "contact.html";
	}
}
