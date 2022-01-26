package com.example.demo.config.web;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@PersistenceContext private EntityManager em;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("*");
	}
	
//	@Bean
//	public TilesViewResolver tilesViewResolver() {
//		final TilesViewResolver resolver = new TilesViewResolver();
//		resolver.setViewClass(TilesView.class);
//		resolver.setOrder(1);
//		return resolver;
//	}
//	
//	@Bean
//	public RestTemplate myRestTemplateBuilder(RestTemplateBuilder builder) {
//		builder.setConnectTimeout(Duration.ofMillis(10000));
//		builder.setReadTimeout(Duration.ofMillis(10000));
//		return builder.build();
//	}
	
	@Bean
	public ModelMapper myModelMapper() {
		ModelMapper mm = new ModelMapper();
		mm.getConfiguration().setSkipNullEnabled(true);
		mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mm;
	}
	
	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(em);
	}
}
