package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired private AuthEntryPoint_DENIED aep;

	@Bean
	public PasswordEncoder pe() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/files/**", "/swagger-ui/**", "/v3/api-docs/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//api
		http.cors().and().csrf().disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.exceptionHandling()
				.authenticationEntryPoint(aep)
			.and()
			.authorizeHttpRequests()
				.antMatchers("/api/*/samp/**", "/api/*/auth/**").permitAll()
				.antMatchers("/api/**").authenticated()
			.and()
			.formLogin().disable();
				
		//public
		http.cors().and().csrf().disable()
			.authorizeRequests()
				.antMatchers("/public/**", "/favicon*", "/api/**").permitAll()
				.antMatchers("/**").authenticated()
			.and()
			.formLogin()
				.loginPage("/public/login")
				.permitAll()
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/public/login")
				.invalidateHttpSession(true)
				.and()
			.exceptionHandling()
				.accessDeniedPage("/public/denied");
	}
}
