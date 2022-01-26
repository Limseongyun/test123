package com.example.demo.cmm.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.cmm.utils.JwtTokenProvider;
import com.example.demo.config.security.CustomUserDetailService;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired private CustomUserDetailService cds;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.debug("[JwtAuthenticationFilter]{}, {}", request.getRequestURI(), request.getHeader("Authorization"));
		
		if(request.getRequestURI().startsWith("/api")) {
			log.debug("jwt 인증 진행");
			request.getSession().invalidate();
			jwtAuthStart(request);
			request.setAttribute("isApi", true);
		}else {
			request.setAttribute("isApi", false);
		}
		filterChain.doFilter(request, response);
	}

	private void jwtAuthStart(HttpServletRequest req) {
		try {
			String jwt = getJwtFromRequest(req);
			log.debug("jwt is {}", jwt);
			log.debug("cds is {}", cds);
			if(jwt != null && JwtTokenProvider.validateToken(jwt)) {
				Claims claims = JwtTokenProvider.getClaims(jwt);
				String membId = String.valueOf(claims.get("membId"));
				UserDetails ud = cds.loadUserByUsername(membId);
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("인증과정중 오류 발생, {}", e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	private String getJwtFromRequest(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		}
		return null;
	}

}
