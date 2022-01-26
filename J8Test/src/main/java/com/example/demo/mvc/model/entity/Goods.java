package com.example.demo.mvc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_goods", indexes = @Index(columnList = "goods_sn"))
//@SequenceGenerator(name = "goods_seq", allocationSize = 1, initialValue = 1, sequenceName = "goods_seq")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Goods extends Base{
	//상품번호
	@Id
	//@GeneratedValue(generator = "goods_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "goods_sn", length = 15)
	private String goodsSn;
	
	//가맹점 정보
	@ManyToOne@JsonBackReference
	@JoinColumn(name = "merchant_sn")
	private Merchant merchantSn;
	
	//상품명
	@Column(name = "goods_nm", length = 200, nullable = false)
	private String goodsNm;
	
	//상품명
	@Column(name = "goods_model_no", length = 30)
	private String goodsModelNo;
	
	//상품금액
	@Column(name = "goods_amt", length = 15)
	private Long goodsAmt;
	
	//상품수량
	@Column(name = "goods_qtt", length = 8)
	private Long goodsQtt;
	
	//판매수량
	@Column(name = "goods_sell_qtt", length = 8)
	private Long goodsSellQtt;
	
	//판매종료일자
	@Column(name = "goods_cls_dt", length = 8)
	private String goodsClsDt;
	
	//배송비용
	@Column(name = "goods_shpp_cost", length = 6)
	private Long goodsShppCost;
	
	//실제파일명
	@Column(name = "real_file_nm", length = 100)
	private String realFileNm;
	
	//상품이미지경로
	@Column(name = "goods_img_path", length = 200)
	private String goodsImgPath;
	
	//상품설명
	@Column(name = "goods_desc", length = 4000)
	private String goodsDesc;
}
