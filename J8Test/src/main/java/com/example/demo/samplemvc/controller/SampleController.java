package com.example.demo.samplemvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.samplemvc.service.SampleService;

@Controller
@RequestMapping("/public/samp/")
public class SampleController {
	@Autowired private SampleService service;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("sample", service.batisParent(null));
		return "sample/index";
	}
	
	@GetMapping("/500")
	public String t500(Model model) {
		int t = 1/0;
		return "pages/index";
	}
}
