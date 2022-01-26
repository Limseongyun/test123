package com.example.demo.samplemvc.entity;

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
@Table(name = "tb_sample_parent", indexes = @Index(columnList = "parent_sn"))
@SequenceGenerator(name = "parent_seq", allocationSize = 1, initialValue = 1, sequenceName = "parent_seq")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
public class SampleParent extends Base{
	@Id@GeneratedValue(generator = "parent_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "parent_sn")
	private Long parentSn;
	
	@Column(name = "parent_nm")
	private String parentNm;
	
	@OneToMany(mappedBy = "childSn")
	@JsonManagedReference
	private List<SampleChild> childs = new ArrayList<>();
}
