package com.example.demo.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InitConfig {

	@PostConstruct
	public void init() {
		log.debug("initConfig - start");
	}
}
