package com.example.demo.mvc.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.example.demo.mvc.model.entity.Base;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_merchant", indexes = @Index(columnList = "merchant_sn"))
@SequenceGenerator(name = "merchant_seq", allocationSize = 1, initialValue = 1, sequenceName = "merchant_seq")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Merchant extends Base{
	@Id@GeneratedValue(generator = "merchant_seq", strategy = GenerationType.SEQUENCE)@Column(name = "merchant_sn")
	private Long merchantSn;
	
	@Column(name = "merchant_nm", length = 100, nullable = false)
	private String merchantNm;
	
	@Column(name = "merchant_desc", length = 400)
	private String merchantDesc;
	
	@Column(name = "merchant_url", length = 100)
	private String merchantUrl;
	
	@Column(name = "tel_no", length = 20)
	private String telNo;
	
	@Column(name = "email_addr", length = 100)
	private String EmailAddr;
	
	@Column(name = "zip_cd", length = 6)
	private String zipCd;
	
	@Column(name = "zip_addr", length = 150)
	private String zipAddr;
	
	@Column(name = "detail_addr", length = 150)
	private String detailAddr;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "goodsSn", cascade = CascadeType.ALL)
	private List<Goods> goodslist = new ArrayList<Goods>();
}
