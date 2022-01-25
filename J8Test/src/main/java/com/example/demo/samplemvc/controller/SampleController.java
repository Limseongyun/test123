package com.example.demo.samplemvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public/samp/")
public class SampleController {

	@GetMapping("/index")
	public String index(Model model) {
		return "sample/index";
	}
	
	@GetMapping("/500")
	public String t500(Model model) {
		int t = 1/0;
		return "pages/index";
	}
}
