package com.example.CareerGuidance.CareerGuidance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.CareerGuidance.CareerGuidance.model.Register;
import com.example.CareerGuidance.CareerGuidance.service.RegisterService;

import ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod;

@Controller
public class RegisterController {
	private final RegisterService registerService;
    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


	@GetMapping(value={"/register.html"})
	public String displayRegister() {
		return "register";
	}
	@PostMapping("/saveMsg")
    public ModelAndView saveMessage(@ModelAttribute Register register){
		
	    registerService.saveMessageDetails(register);  // Save to DB
	    return new ModelAndView("redirect:/front.html");
   }
	
	
}

