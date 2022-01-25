package com.example.demo.samplemvc.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class SampleRestController2 {

	@GetMapping("/test1")
	public String test1() {
		return "test1";
	}
}
