package com.example.demo.cmm.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenProvider {
	// TODO: @Value
	private final static String JWT_SECRET = "password";
	private final static int JWT_EXPIRATION_MS = 1000 * 60 * 10;
	
	public static String generateToken(String id, String pw, String authority) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", authority);
		claims.put("membId", id);
		claims.put("membPw", pw);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_MS))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
				.compact();
	}
	
	public static Claims getClaims(String jwt) {
		Claims claims = Jwts.parser()
				.setSigningKey(JWT_SECRET)
				.parseClaimsJws(jwt)
				.getBody();
		return claims;
	}
	
	public static boolean validateToken(String token) {
		return validateTokenSub(token, null);
	}
	
	public static boolean validateToken(String token, HttpServletRequest req) {
		return validateTokenSub(token, req);
	}
	
	private static boolean validateTokenSub(String token, HttpServletRequest req) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			String err = "JWT 사이닝이 오류";
			log.error(err);
			if(req != null) req.setAttribute("unauthorization", err);
		} catch (MalformedJwtException ex) {
			String err = "JWT 잘못됨 MalformedJwtException";
			log.error(err);
			if(req != null) req.setAttribute("unauthorization", err);
		} catch (ExpiredJwtException ex) {
			String err = "JWT 만료";
			log.error(err);
			if(req != null) req.setAttribute("unauthorization", err);
		} catch (UnsupportedJwtException ex) {
			String err = "JWT 지원하지 않는 형식";
			log.error(err);
			if(req != null) req.setAttribute("unauthorization", err);
		} catch (IllegalArgumentException ex) {
			String err = "JWT 잘못됨 IllegalArgumentException";
			log.error(err);
			if(req != null) req.setAttribute("unauthorization", err);
		}
		return false;
	}
}