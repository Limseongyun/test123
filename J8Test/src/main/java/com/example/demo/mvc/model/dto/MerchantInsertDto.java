package com.example.demo.mvc.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MerchantInsertDto {
	@NonNull
	private String merchantNm;
	private String merchantDesc;
	private String merchantUrl;
	private String telNo;
	private String EmailAddr;
	private String zipCd;
	private String zipAddr;
	private String detailAddr;
}
