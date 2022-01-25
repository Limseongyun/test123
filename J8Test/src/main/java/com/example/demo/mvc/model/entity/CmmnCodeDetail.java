package com.example.demo.mvc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Table(name = "tb_cmmn_code_detail")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "cmm_code_detail_seq", allocationSize = 1, initialValue = 1, sequenceName = "cmm_code_detail_seq")
public class CmmnCodeDetail extends BaseTime{
	@Id
	@GeneratedValue(generator = "cmm_code_detail_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "cmmn_code_detail_sn")
	private Long cmmnCodeDetailSn;
	
	@Column(name = "code_id")
	private String codeId;
	
	@Column(name = "code_value")
	private String codeValue;
	
	@Column(name = "code_value_nm")
	private String codeValueNm;
}
