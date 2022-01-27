package com.example.demo.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.cmm.code.Cd;
import com.example.demo.cmm.utils.EntityUtil;
import com.example.demo.mvc.model.dto.ChargeDto;
import com.example.demo.mvc.model.entity.Member;
import com.example.demo.mvc.model.entity.MemberMoney;
import com.example.demo.mvc.model.entity.Merchant;
import com.example.demo.mvc.model.entity.MoneyTransferHst;
import com.example.demo.mvc.model.entity.QMember;
import com.example.demo.mvc.model.entity.QMerchant;
import com.example.demo.mvc.repository.MemberMoneyRepo;
import com.example.demo.mvc.repository.MoneyTransferHstRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserService {
	@Autowired private JPAQueryFactory qf;
	@Autowired private MemberMoneyRepo mmRepo;
	@Autowired private MoneyTransferHstRepo moneyTrfHstRepo;
	@Autowired private EntityUtil eu;
	
	//충전
	public MemberMoney charge(Member member, ChargeDto chargeDto) {
		QMember qmem = QMember.member;
		MemberMoney mem = (MemberMoney) qf.selectFrom(qmem).where(qmem.memberSn.eq(member.getMemberSn())).fetchOne();
		MoneyTransferHst mtf = new MoneyTransferHst();
		//충전
		mtf.setTransferTyCd(eu.getMoneyTransferCmm(Cd.MONEY_TSF_TY_CHARGE));
		//충전방식은 dto에서 온대로
		mtf.setPayMeanCd(eu.getMoneyMeanCmm(chargeDto.getChargeMean()));
		mtf.setMemberSn(mem);
		mtf.setTransferAmt(chargeDto.getChargeAmt());
		moneyTrfHstRepo.save(mtf);
		mem.setMoneyBlce(mem.getMoneyBlce() + chargeDto.getChargeAmt());
		return mmRepo.save(mem);
	}
	//구매
	
	
	
	
	public List<Merchant> selectAllMerchant(){
		QMerchant qmerchant = QMerchant.merchant;
		return qf.selectFrom(qmerchant).fetch();
	}
	
	//sample
	public List<Member> allMember() {
		QMember qmem = QMember.member;
		return qf.select(qmem)
		  .from(qmem)
		  .where(qmem.useYn.eq("Y"))
		  .offset(0) //paging 언제부터 시작할건지
 		  .limit(5) //paging 몇번째까지 끊을건지
		  .fetch();
	}
}
