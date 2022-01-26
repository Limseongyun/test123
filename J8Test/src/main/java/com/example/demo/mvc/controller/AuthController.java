package com.example.demo.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class AuthController {
	
	@GetMapping("/denied")
	public String denied(Model model) {
		return "pages/deniedPage";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "pages/loginPage";
	}
}
