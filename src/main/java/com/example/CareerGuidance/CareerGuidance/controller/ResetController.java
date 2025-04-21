package com.example.CareerGuidance.CareerGuidance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResetController {
	@RequestMapping(value={"reset.html"})
	public String displayResetPassword() {
		return "reset.html";
	}

}
