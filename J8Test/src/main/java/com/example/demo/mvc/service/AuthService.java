package com.example.demo.mvc.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.cmm.code.Cd;
import com.example.demo.cmm.utils.EntityUtil;
import com.example.demo.cmm.utils.JwtTokenProvider;
import com.example.demo.mvc.model.dto.JoinDto;
import com.example.demo.mvc.model.entity.Member;
import com.example.demo.mvc.model.entity.MemberLoginHst;
import com.example.demo.mvc.model.entity.MemberMoney;
import com.example.demo.mvc.model.entity.QMember;
import com.example.demo.mvc.repository.MemberLoginHstRepo;
import com.example.demo.mvc.repository.MemberMoneyRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AuthService {
	@Autowired private PasswordEncoder pe;
	@Autowired private JPAQueryFactory qf;
	@Autowired private ModelMapper mm;
	@Autowired private MemberMoneyRepo mmRepo;
	@Autowired private EntityUtil eu;
	@Autowired private MemberLoginHstRepo memLoginHstRepo;
	
	public String gettoken(String id, String pw, String ip) {
		QMember qmem = QMember.member;
		Member mem = qf.selectFrom(qmem).where(qmem.membId.eq(id)).fetchOne();
		
		if(mem == null) {
			throw new RuntimeException("존재하지 않는 유저 입니다.");
		}
		
		if(! pe.matches(pw, mem.getPassword())) {
			throw new RuntimeException("비밀번호가 틀립니다.");
		}
		if(! Cd.MEMBER_STTUS_OK.equals(mem.getMembSttusCd().getCodeValue())) {
			throw new RuntimeException("유저 상태가 정상이 아닙니다.");
		}
		MemberLoginHst mlg = new MemberLoginHst();
		mlg.setConnectIp(ip);
		mlg.setMemberSn(mem);
		memLoginHstRepo.save(mlg);
		return JwtTokenProvider.generateToken(id, pw, mem.getMembCls().getCodeValue());
	}
	
	public MemberMoney sellerJoin(JoinDto joinDto) {
		QMember qmem = QMember.member;
		
		Member memChk = qf.selectFrom(qmem).where(qmem.membId.eq(joinDto.getMembId())).fetchOne();
		if(memChk != null) {
			throw new RuntimeException("이미 존재하는 아이디 입니다.");
		}
		MemberMoney newMem = mm.map(joinDto, MemberMoney.class);
		newMem.setMoneyBlce(0L);
		newMem.setMembPw(pe.encode(newMem.getPassword()));
		newMem.setMembCls(eu.getMemberTyCmm(Cd.MEMBER_TY_SELLER));
		newMem.setMembSttusCd(eu.getMemberSttusCmm(Cd.MEMBER_STTUS_OK));
		return mmRepo.save(newMem);
	}
	
	public MemberMoney userJoin(JoinDto joinDto) {
		QMember qmem = QMember.member;
		
		Member memChk = qf.selectFrom(qmem).where(qmem.membId.eq(joinDto.getMembId())).fetchOne();
		if(memChk != null) {
			throw new RuntimeException("이미 존재하는 아이디 입니다.");
		}
		MemberMoney newMem = mm.map(joinDto, MemberMoney.class);
		newMem.setMoneyBlce(0L);
		newMem.setMembPw(pe.encode(newMem.getPassword()));
		newMem.setMembCls(eu.getMemberTyCmm(Cd.MEMBER_TY_USER));
		newMem.setMembSttusCd(eu.getMemberSttusCmm(Cd.MEMBER_STTUS_OK));
		return mmRepo.save(newMem);
	}
}
