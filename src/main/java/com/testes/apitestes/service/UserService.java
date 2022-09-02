package com.testes.apitestes.service;

import java.util.List;

import com.testes.apitestes.model.dto.UserDTO;
import com.testes.apitestes.model.entity.UserEntity;

public interface UserService {

	UserEntity findById(Integer id);
	
	UserEntity save(UserDTO user);

	UserEntity update(UserDTO user);
	
	List<UserEntity> findAll();
	
	List<UserEntity> saveAll(List<UserEntity> users); 

	void delete(Integer id);
}
