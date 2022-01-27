package com.example.demo.mvc.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cmm.code.ApiCd;
import com.example.demo.cmm.rvo.RVO;
import com.example.demo.mvc.model.dto.ChargeDto;
import com.example.demo.mvc.model.entity.Member;
import com.example.demo.mvc.model.entity.MemberMoney;
import com.example.demo.mvc.service.UserService;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {
	@Autowired private UserService userService;
	
	@GetMapping("/test")
	public String test() {
		return "testUser";
	}
	
	@PostMapping("/charge")
	public RVO<MemberMoney> charge(
			@Parameter(hidden = true) @AuthenticationPrincipal Member member,
			@RequestBody ChargeDto chargeDto) {
		return RVO.<MemberMoney>builder()
				.msg("충전이 완료되었습니다.")
				.code(ApiCd.NORMAL)
				.data(userService.charge(member, chargeDto))
				.build();
	}
	//구매
	//사용기록 조회
	//충전
	
	//sample
	@GetMapping("/members")
	public RVO<List<Member>> allMember() {
		return RVO.<List<Member>>builder()
				.msg("테스트 : 모든 맴버입니다.")
				.code(ApiCd.NORMAL)
				.data(userService.allMember())
				.build();
	}
	
}
