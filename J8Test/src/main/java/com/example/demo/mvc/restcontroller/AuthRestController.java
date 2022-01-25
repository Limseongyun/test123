package com.example.demo.mvc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cmm.code.ApiCd;
import com.example.demo.cmm.rvo.RVO;
import com.example.demo.mvc.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
	@Autowired private AuthService authService;
	
	
	@GetMapping("/token")
	public RVO<String> token(String id, String pw){
		return RVO.<String>builder()
				.msg("JWT 토큰이 생성되었습니다.")
				.code(ApiCd.NORMAL)
				.data(authService.gettoken(id, pw))
				.build();
	}

}
