package com.example.demo.mvc.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerRestController {
	@GetMapping("/test")
	public String test() {
		return "testSeller";
	}
}
