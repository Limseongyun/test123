package com.example.demo.mvc.restcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cmm.code.ApiCd;
import com.example.demo.cmm.rvo.RVO;
import com.example.demo.mvc.model.dto.JoinDto;
import com.example.demo.mvc.model.entity.Member;
import com.example.demo.mvc.model.entity.MemberMoney;
import com.example.demo.mvc.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
	@Autowired private AuthService authService;
	
	
	@GetMapping("/token")
	public RVO<String> token(HttpServletRequest request, String id, String pw){
		return RVO.<String>builder()
				.msg("JWT 토큰이 생성되었습니다.")
				.code(ApiCd.NORMAL)
				.data(authService.gettoken(id, pw, request.getRemoteAddr()))
				.build();
	}
	
	@PostMapping("/sellerJoin")
	public RVO<Member> sellerJoin(@RequestBody JoinDto joinDto){
		return RVO.<Member>builder()
				.msg("판매자 가입 되었습니다.")
				.code(ApiCd.NORMAL)
				.data(authService.sellerJoin(joinDto))
				.build();
	}
	
	@PostMapping("/userJoin")
	public RVO<MemberMoney> userJoin(@RequestBody JoinDto joinDto){
		return RVO.<MemberMoney>builder()
				.msg("사용자 가입 되었습니다.")
				.code(ApiCd.NORMAL)
				.data(authService.userJoin(joinDto))
				.build();
	}
	
	@PostMapping("/resign")
	public RVO<Member> resign(){
		return RVO.<Member>builder()
				.msg("탈퇴에 성공하였습니다.")
				.code(ApiCd.NORMAL)
				.data(authService.resign())
				.build();
	}

}
