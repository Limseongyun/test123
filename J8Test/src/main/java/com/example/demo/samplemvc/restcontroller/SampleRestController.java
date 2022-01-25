package com.example.demo.samplemvc.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/samp")
public class SampleRestController {

	@GetMapping("/samp1")
	public String samp1() {
		return "sampe1한글?";
	}
}
