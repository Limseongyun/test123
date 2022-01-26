package com.example.demo.mvc.model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
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
@Table(name = "tb_memb_money")
//@SequenceGenerator(name = "memLoginHst_seq", allocationSize = 1, initialValue = 1, sequenceName = "memLoginHst_seq")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
@DiscriminatorValue("member_money")
public class MemberMoney extends Member{	
	private static final long serialVersionUID = 1L;
	@Column(name = "money_blce", length = 15)
	@ColumnDefault("0")
	private Long moneyBlce;
}
