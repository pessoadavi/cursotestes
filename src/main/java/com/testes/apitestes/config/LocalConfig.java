package com.testes.apitestes.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.testes.apitestes.model.entity.UserEntity;
import com.testes.apitestes.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@Profile("local")
@RequiredArgsConstructor
public class LocalConfig {

	private final UserService userService;
	
	@Bean
	public void startDB() {
//		UserEntity user1 = new UserEntity(1, "Davi", "davi@davi.com", "123");
//		UserEntity user2 = new UserEntity(2, "Rafa", "rafa@rafa.com", "123");
//	
//		userService.saveAll(List.of(user1, user2));
	}
}
