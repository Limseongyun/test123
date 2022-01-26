package com.example.demo.mvc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cmm.code.ApiCd;
import com.example.demo.cmm.rvo.RVO;
import com.example.demo.mvc.model.dto.GoodsInsertDto;
import com.example.demo.mvc.model.dto.MerchantInsertDto;
import com.example.demo.mvc.model.entity.Goods;
import com.example.demo.mvc.model.entity.Merchant;
import com.example.demo.mvc.service.SellerService;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerRestController {
	@Autowired private SellerService sellerService;
	
	@GetMapping("/test")
	public String test() {
		return "testSeller";
	}
	//가맹점등록
	@PostMapping("/merchant")
	public RVO<Merchant> insertMerchant(@RequestBody MerchantInsertDto mDto) {
		return RVO.<Merchant>builder()
				.code(ApiCd.NORMAL)
				.msg("가맹점 등록이 완료되었습니다.")
				.data(sellerService.insertMerchang(mDto))
				.build();
	}
	
	//상품등록
	@PostMapping("/goods")
	public RVO<Goods> insertGoods(@RequestBody GoodsInsertDto gDto){
		return RVO.<Goods>builder()
				.code(ApiCd.NORMAL)
				.msg("상품등록이 완료되었습니다.")
				.data(sellerService.insertGoods(gDto))
				.build();
	}
}
