package com.example.demo.mvc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_money_transfer_hst", indexes = @Index(columnList = "money_transfer_hst_sn"))
@SequenceGenerator(name = "money_tsf_hst_seq", allocationSize = 1, initialValue = 1, sequenceName = "money_tsf_hst_seq")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MoneyTransferHst extends Base{
	@Id@GeneratedValue(generator = "money_tsf_hst_seq", strategy = GenerationType.SEQUENCE)@Column(name = "money_transfer_hst_sn")
	private Long moneyTransferHstSn;
	
	@ManyToOne
	@JoinColumn(name = "member_sn")
	private Member memberSn;
	
	@OneToOne
	@JoinColumn(name = "transfer_ty_cd")
	private CmmnCodeDetail transferTyCd;
	
	@OneToOne//박재훈님이 추가
	@JoinColumn(name = "buy_hst_sn")
	private BuyHst buyHst;
	
	@Column(name = "transfer_amt", length = 15)
	private Long transferAmt;
	
	@OneToOne
	@JoinColumn(name = "pay_mean_cd")
	private CmmnCodeDetail payMeanCd;
	
	@Column(name = "pay_transfer_no", length = 20)
	private String payTransferNo;
}
