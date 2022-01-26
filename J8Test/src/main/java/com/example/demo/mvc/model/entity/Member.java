package com.example.demo.mvc.model.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.cmm.code.Cd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_member", indexes = @Index(columnList = "memb_sn"))
@SequenceGenerator(name = "member_seq", allocationSize = 1, initialValue = 1, sequenceName = "member_seq")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity

@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "DTYPE")
public class Member extends Base implements UserDetails{
	private static final long serialVersionUID = 1L;

	//회원번호
	@Id@Column(name = "memb_sn")@GeneratedValue(generator = "member_seq", strategy = GenerationType.SEQUENCE)
	private Long memberSn;
	
	//회원구분
	@OneToOne@JoinColumn(name = Cd.CODE_ID_MEMBER_TY)
	private CmmnCodeDetail membCls;
	
	//회원상태
	@OneToOne@JoinColumn(name = Cd.CODE_ID_MEMBER_STTUS)
	private CmmnCodeDetail membSttusCd;
	
	//회원ID
	@Column(name = "memb_id", length = 12, nullable = false, unique = true)
	private String membId;
	
	//회원PW
	@Column(name = "memb_pw", length = 200, nullable = false)
	private String membPw;
	
	//회원이름
	@Column(name = "memb_nm", length = 100, nullable = false)
	private String membNm;
	
	//휴대폰번호
	@Column(name = "mobile_no", length = 20)
	private String mobileNo;
	
	//이메일주소
	@Column(name = "email_addr", length = 100)
	private String emailAddr;
	
	//우편번호
	@Column(name = "zip_cd", length = 6)
	private String zipCd;
	
	//우편번호주소
	@Column(name = "zip_addr", length = 150)
	private String zipAddr;
	
	//상세주소
	@Column(name = "detail_addr", length = 150)
	private String detailAddr;
	
	//최종로그인일시
	@Column(name = "last_login_dtm", length = 14)
	private String lastLoginDtm;

	//-------------------------------------------------------
	//SecurityConfig
	//-------------------------------------------------------
	@Transient
	private Collection<SimpleGrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.membPw;
	}

	@Override
	public String getUsername() {//ID? SN? 
		return String.valueOf(this.membId);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
