package com.example.demo.cmm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private CustomUserDetailService cds;
	private PasswordEncoder pe;
	
	CustomAuthenticationProvider(@Lazy CustomUserDetailService cds, @Lazy PasswordEncoder pe){
		this.cds = cds;
		this.pe = pe;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String id = String.valueOf(authentication.getPrincipal());
		String pw = String.valueOf(authentication.getCredentials());
		
		UserDetails ud = cds.loadUserByUsername(id);
		if(! pe.matches(pw, ud.getPassword())) {
			throw new RuntimeException("비밀번호가 틀립니다.");
		}
		
		UsernamePasswordAuthenticationToken res = new UsernamePasswordAuthenticationToken(ud, pw, ud.getAuthorities());
		return res;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		//return authentication.equals(UsernamePasswordAuthenticationToken.class);
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}