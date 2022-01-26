package com.example.demo.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.cmm.CommonMap;
import com.example.demo.mvc.model.entity.Member;
import com.example.demo.mvc.service.UserService;

@Controller
public class IndexController {
	@Autowired private UserService userService;
	
	@GetMapping("/index")
	public String index(@AuthenticationPrincipal Member member, Model model) {
		model.addAttribute("member",member);
		model.addAttribute("merchants", userService.selectAllMerchant());
		return "pages/indexPage";
	}
	
	@GetMapping("/ajaxTest")
	@ResponseBody
	public CommonMap ajaxtest() {
		CommonMap map = new CommonMap();
		map.set("hi", "immap");
		return map;
	}
}
