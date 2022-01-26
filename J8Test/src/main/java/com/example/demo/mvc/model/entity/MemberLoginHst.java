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
@Table(name = "tb_memb_login_hst", indexes = @Index(columnList = "login_sn"))
@SequenceGenerator(name = "memLoginHst_seq", allocationSize = 1, initialValue = 1, sequenceName = "memLoginHst_seq")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MemberLoginHst extends Base{
	@Id@GeneratedValue(generator = "memLoginHst_seq", strategy = GenerationType.SEQUENCE)@Column(name = "login_sn")
	private Long loginSn;
	
	@ManyToOne@JoinColumn(name = "member_sn")
	private Member memberSn;
	
	@Column(name = "connect_ip")
	private String connectIp;
}
