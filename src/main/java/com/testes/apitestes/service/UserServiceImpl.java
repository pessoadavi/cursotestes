package com.testes.apitestes.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testes.apitestes.exceptions.DataIntegrityViolationException;
import com.testes.apitestes.exceptions.ObjectNotFoundException;
import com.testes.apitestes.model.dto.UserDTO;
import com.testes.apitestes.model.entity.UserEntity;
import com.testes.apitestes.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private @Autowired ModelMapper modelMapper;
	private @Autowired UserRepository userRepository;
	
	@Override
	public UserEntity findById(Integer id) {
		return userRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Usuário não encontrado."));
	}

	@Override
	public UserEntity save(UserDTO user) {
		checkEmail(user);
		return userRepository.saveAndFlush(modelMapper.map(user, UserEntity.class));
	}

	@Override
	public List<UserEntity> saveAll(List<UserEntity> users) {
		return userRepository.saveAllAndFlush(users);
	}
	
	@Override
	public UserEntity update(UserDTO user) {
		checkEmail(user);
		return userRepository.saveAndFlush(modelMapper.map(user, UserEntity.class));
	}

	@Override
	public void delete(Integer id) {
		findById(id);
		userRepository.deleteById(id);	
	}
	
	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}
	
	private void checkEmail(UserDTO user) {
		Optional<UserEntity> result = userRepository.findByEmail(user.getEmail());
		if (result.isPresent() && !result.get().getId().equals(user.getId())) {
			throw new DataIntegrityViolationException("E-mail já cadastro. Informe um diferente.");
		}
	}

}
