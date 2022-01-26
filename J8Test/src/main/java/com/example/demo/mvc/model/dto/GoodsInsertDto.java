package com.example.demo.mvc.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class GoodsInsertDto {
	private Long merchantSn;
	private String goodsNm;
	private String goodsModelNo;
	private Long goodsAmt;
	private Long goodsQtt;
	private String goodsClsDt;
	private Long goodsShppCost;
	private String realFileNm;
	private String goodsImgPath;
	private String goodsDesc;
}
