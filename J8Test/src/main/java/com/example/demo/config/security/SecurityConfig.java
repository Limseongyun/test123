package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.cmm.filters.JwtAuthenticationFilter;
import com.example.demo.cmm.security.AuthEntryPoint_DENIED;
import com.example.demo.cmm.security.CustomAccessDeniedHandler;
import com.example.demo.cmm.security.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired private AuthEntryPoint_DENIED aep;
	@Autowired private JwtAuthenticationFilter jwtFilter;
	@Autowired private CustomAccessDeniedHandler cad;
	@Autowired private CustomAuthenticationProvider cap;

	@Bean
	public PasswordEncoder pe() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/public/**", "/error/**", "/webjars/**", "/files/**", "/swagger-ui/**", "/v3/api-docs/**", "/favicon*", "/WEB-INF/views/error/errorPage.jsp");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(cap);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.exceptionHandling()
					.authenticationEntryPoint(aep)
					.accessDeniedHandler(cad)
				.and()
				.authorizeHttpRequests()
					.antMatchers("/api/*/samp/**", "/api/*/auth/**").permitAll()
					.antMatchers("/api/*/admin/**").hasAuthority("ROLE_ADMIN")
					.antMatchers("/api/*/seller/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SELLER")
					.antMatchers("/api/*/user/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SELLER", "ROLE_USER")
					.antMatchers("/public/**", "/api/**").permitAll()
					.anyRequest().authenticated()
				.and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.formLogin()
					.loginPage("/public/login")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/index")
				.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/public/login")
					.invalidateHttpSession(true);
	}
}
