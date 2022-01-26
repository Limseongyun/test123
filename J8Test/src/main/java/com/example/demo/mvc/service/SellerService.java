package com.example.demo.mvc.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mvc.model.dto.GoodsInsertDto;
import com.example.demo.mvc.model.dto.MerchantInsertDto;
import com.example.demo.mvc.model.entity.Goods;
import com.example.demo.mvc.model.entity.Merchant;
import com.example.demo.mvc.model.entity.QGoods;
import com.example.demo.mvc.repository.GoodsRepo;
import com.example.demo.mvc.repository.MerchantRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SellerService {
	
	@Autowired private JPAQueryFactory qf;
	@Autowired private GoodsRepo goodsRepo;
	@Autowired private MerchantRepo merchantRepo;
	@Autowired private ModelMapper mm;
	
	public Merchant insertMerchang(MerchantInsertDto mDto) {
		Merchant merchant = mm.map(mDto, Merchant.class);
		return merchantRepo.save(merchant);
	}
	
	public Goods insertGoods(GoodsInsertDto gDto) {
		Goods goods = mm.map(gDto, Goods.class);
		Optional<Merchant> mer = merchantRepo.findById(gDto.getMerchantSn());
		if(! mer.isPresent()) {
			throw new RuntimeException("가맹점이 존재하지 않습니다.");
		}
		goods.setMerchantSn(mer.get());
		goods.setGoodsSellQtt(0L);
		//YYYYMMDD(8) + 7 ///15
		String todate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		QGoods qgoods = QGoods.goods;
		List<String> strs = qf.select(qgoods.goodsSn).from(qgoods).where(qgoods.goodsSn.contains(todate)).fetch();
		log.debug("stsr : {}, {}, {}", strs, strs.size(), todate + String.format("%07d", strs.size()+1));
		
		goods.setGoodsSn(todate + String.format("%07d", strs.size()+1));
		return goodsRepo.save(goods);
	}

}
