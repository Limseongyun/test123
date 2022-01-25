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
		}
		filterChain.doFilter(request, response);
	}

}
