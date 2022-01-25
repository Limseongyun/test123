package com.example.demo.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.cmm.code.Cd;
import com.example.demo.cmm.utils.EntityUtil;
import com.example.demo.mvc.model.entity.CmmnCodeDetail;
import com.example.demo.mvc.model.entity.Member;
import com.example.demo.mvc.model.entity.MemberMoney;
import com.example.demo.mvc.model.entity.MemberMoney;
import com.example.demo.mvc.repository.CmmnCodeDetailRepo;
import com.example.demo.mvc.repository.MemberMoneyRepo;
import com.example.demo.mvc.repository.MemberRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InitConfig {
	@Autowired private PasswordEncoder pe;
	@Autowired private EntityUtil eu;
	@Autowired private CmmnCodeDetailRepo ccdRepo;
	@Autowired private MemberRepo memRepo;
	@Autowired private MemberMoneyRepo mmRepo;

	@PostConstruct
	public void init() {
log.debug("INIT START");
		
		Member mem = memRepo.findByMembId("admin");
		log.debug("mem {}", mem);
		if(mem == null) {
			log.debug("admin 없음, 초기코드, 어드민 생성");
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MEMBER_STTUS, Cd.MEMBER_STTUS_OK, Cd.MEMBER_STTUS_OK_NM));
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MEMBER_STTUS, Cd.MEMBER_STTUS_DORMACY, Cd.MEMBER_STTUS_DORMACY_NM));
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MEMBER_STTUS, Cd.MEMBER_STTUS_RESIGN, Cd.MEMBER_STTUS_RESIGN_NM));
			
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MEMBER_TY, Cd.MEMBER_TY_ADMIN, Cd.MEMBER_TY_ADMIN_NM));
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MEMBER_TY, Cd.MEMBER_TY_SELLER, Cd.MEMBER_TY_SELLER_NM));
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MEMBER_TY, Cd.MEMBER_TY_USER, Cd.MEMBER_TY_USER_NM));
			
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MONEY_TSF_TY_CODE, Cd.MONEY_TSF_TY_CHARGE, Cd.MONEY_TSF_TY_CHARGE_NM));
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MONEY_TSF_TY_CODE, Cd.MONEY_TSF_TY_EXCHANGE, Cd.MONEY_TSF_TY_EXCHANGE_NM));
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MONEY_TSF_TY_CODE, Cd.MONEY_TSF_TY_USE, Cd.MONEY_TSF_TY_USE_NM));
			
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MONEY_MEAN_CODE, Cd.MONEY_MEAN_ACNT, Cd.MONEY_MEAN_ACNT_NM));
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MONEY_MEAN_CODE, Cd.MONEY_MEAN_CARD, Cd.MONEY_MEAN_CARD_NM));
			ccdRepo.save(genCmmnCode(Cd.CODE_ID_MONEY_MEAN_CODE, Cd.MONEY_MEAN_MONEY, Cd.MONEY_MEAN_MONEY_NM));
			
//			Member admin = new Member();
//			admin.setMembCls(eu.getMemberTyCmm(Cd.MEMBER_TY_ADMIN));
//			admin.setMembSttusCd(eu.getMemberSttusCmm(Cd.MEMBER_STTUS_OK));
//			admin.setMembNm("admin");
//			admin.setMobileNo("01000000000");
//			admin.setEmailAddr("lsy96999@gmail.com");
//			admin.setMembId("admin");
//			admin.setMembPw(pe.encode("admin"));
//			memRepo.save(admin);
			
			MemberMoney admin = new MemberMoney();
			admin.setMembCls(eu.getMemberTyCmm(Cd.MEMBER_TY_ADMIN));
			admin.setMembSttusCd(eu.getMemberSttusCmm(Cd.MEMBER_STTUS_OK));
			admin.setMembNm("admin");
			admin.setMobileNo("01000000000");
			admin.setEmailAddr("lsy96999@gmail.com");
			admin.setMembId("admin");
			admin.setMembPw(pe.encode("admin"));
			admin.setMoneyBlce(99999999L);
			mmRepo.save(admin);
			log.debug("admin {}", admin);
		}
	}
	private CmmnCodeDetail genCmmnCode(String codeId, String codeVal, String codeValNm) {
		CmmnCodeDetail ccd = new CmmnCodeDetail();
		ccd.setCodeId(codeId);
		ccd.setCodeValue(codeVal);
		ccd.setCodeValueNm(codeValNm);
		return ccd;
	}
}
