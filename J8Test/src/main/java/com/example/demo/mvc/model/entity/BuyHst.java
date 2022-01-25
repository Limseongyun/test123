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
@Table(name = "tb_buy_hst", indexes = @Index(columnList = "buy_hst_sn"))
@SequenceGenerator(name = "buy_hst_seq", allocationSize = 1, initialValue = 1, sequenceName = "buy_hst_seq")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
public class BuyHst extends Base{
	@Id@GeneratedValue(generator = "buy_hst_seq", strategy = GenerationType.SEQUENCE)@Column(name = "buy_hst_sn")
	private Long buyHstSn;
	
	@ManyToOne
	@JoinColumn(name = "member_sn")
	private Member memberSn;
	
	@ManyToOne
	@JoinColumn(name = "goods_sn")
	private Goods goodsSn;
	
	@Column(name = "goods_amt", length = 15)
	private Long goodsAmt;
	
	@Column(name = "buy_amt", length = 15)
	private Long buyAmt;
}
