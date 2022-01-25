package com.example.demo.mvc.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.cmm.code.Cd;
import com.example.demo.cmm.utils.JwtTokenProvider;
import com.example.demo.mvc.model.entity.Member;
import com.example.demo.mvc.model.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
	@Autowired private PasswordEncoder pe;
	@Autowired private JPAQueryFactory qf;
	@Autowired private ModelMapper mm;
	
	public String gettoken(String id, String pw) {
		QMember qmem = QMember.member;
		Member mem = qf.selectFrom(qmem).where(qmem.membId.eq(id)).fetchOne();
		
		if(! pe.matches(pw, mem.getPassword())) {
			throw new RuntimeException("비밀번호가 틀립니다.");
		}
		if(! Cd.MEMBER_STTUS_OK.equals(mem.getMembSttusCd().getCodeValue())) {
			throw new RuntimeException("유저 상태가 정상이 아닙니다.");
		}
		return JwtTokenProvider.generateToken(id, pw, mem.getMembCls().getCodeValue());
	}
}
