package com.example.demo.samplemvc.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cmm.code.ApiCd;
import com.example.demo.cmm.rvo.RVO;
import com.example.demo.samplemvc.entity.SampleParent;
import com.example.demo.samplemvc.service.SampleService;

@RestController
@RequestMapping("/api/v1/samp")
public class SampleRestController {
	@Autowired private SampleService ss;

	@GetMapping("/samp1")
	public String samp1() {
		return "sampe1한글?";
	}
	
	@PostMapping("/parent")
	public RVO<SampleParent> addParent() {
		return RVO.<SampleParent>builder().msg("sample parent 인서트ok").code(ApiCd.NORMAL).data(ss.insertParent()).build();
	}
	
	@GetMapping("parents")
	public RVO<List<SampleParent>> getParents(){
		return RVO.<List<SampleParent>>builder().msg("parents!!").code(ApiCd.NORMAL).data(ss.getParents()).build();
	}
	
	@GetMapping("parents2")
	public RVO<List<SampleParent>> getParents2(){
		return RVO.<List<SampleParent>>builder().msg("parents!!").code(ApiCd.NORMAL).data(ss.jpqlParent()).build();
	}
	
	@PostMapping("parent2")
	public RVO<SampleParent> addParents2(){
		return RVO.<SampleParent>builder().msg("parents!!").code(ApiCd.NORMAL).data(ss.emInsertParent()).build();
	}
}
