package com.example.demo.mvc.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
//@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {
	@Column(name = "use_yn", length = 1)
	@ColumnDefault("'Y'")
	private String useYn;
	
	@CreatedDate
	@Column(name = "frst_regist_dt", nullable = false)
	private LocalDateTime frstRegistDt;
	
	@LastModifiedDate
	@Column(name = "last_change_dt", nullable = false)
	private LocalDateTime lastChangeDt;
}
