package com.example.demo.config.jpa;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.mvc.model.entity.Member;
@Component
public class AuditConfig implements AuditorAware<Long>{

	@Override
	public Optional<Long> getCurrentAuditor() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Member mem = (Member) auth.getPrincipal();
		if(auth == null || ! auth.isAuthenticated()) return null;
		if("anonymousUser".equals(auth.getPrincipal())) return null;
		String sn = String.valueOf(mem.getMemberSn());
		return Optional.of(Long.parseLong(sn));
	}

}
