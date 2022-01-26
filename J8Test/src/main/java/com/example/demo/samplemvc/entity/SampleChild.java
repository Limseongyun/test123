package com.example.demo.samplemvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
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

import com.example.demo.mvc.model.entity.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_sample_child", indexes = @Index(columnList = "child_sn"))
@SequenceGenerator(name = "child_seq", allocationSize = 1, initialValue = 1, sequenceName = "child_seq")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
public class SampleChild extends Base{
	@Id@GeneratedValue(generator = "child_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "child_sn")
	private Long childSn;
	
	@Column(name = "child_nm")
	private String childNm;
	
	@JoinColumn(name = "parent_sn")
	@ManyToOne(fetch = FetchType.LAZY)@JsonBackReference
	private SampleParent parent;
}
