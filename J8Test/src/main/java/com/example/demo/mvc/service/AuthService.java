package com.example.demo.mvc.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.cmm.utils.JwtTokenProvider;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
	@Autowired private PasswordEncoder pe;
	@Autowired private JPAQueryFactory qf;
	@Autowired private ModelMapper mm;
	
	
	public String gettoken(String id, String pw) {
		
		return "";
	}
}
