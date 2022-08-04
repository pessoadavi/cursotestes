package com.testes.apitestes.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.testes.apitestes.exceptions.ObjectNotFoundException;
import com.testes.apitestes.model.dto.UserDTO;
import com.testes.apitestes.model.entity.UserEntity;
import com.testes.apitestes.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	
	@Override
	public UserEntity findById(Integer id) {
		return userRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found!!!"));
	}

	@Override
	public UserEntity save(UserDTO user) {
		return userRepository.saveAndFlush(modelMapper.map(user, UserEntity.class));
	}

	@Override
	public List<UserEntity> saveAll(List<UserEntity> users) {
		return userRepository.saveAllAndFlush(users);
	}

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

}
