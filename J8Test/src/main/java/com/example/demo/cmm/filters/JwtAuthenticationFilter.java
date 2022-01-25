package com.example.demo.cmm.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter{@Override
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
			
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("인증과정중 오류 발생, {}", e.getMessage());
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
