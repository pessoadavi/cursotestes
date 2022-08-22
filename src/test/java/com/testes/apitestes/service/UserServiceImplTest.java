package com.testes.apitestes.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.testes.apitestes.model.dto.UserDTO;
import com.testes.apitestes.model.entity.UserEntity;
import com.testes.apitestes.repository.UserRepository;

@SpringBootTest
class UserServiceImplTest {

	private static final Integer ID       = 1;
	private static final String  NAME 	  = "Davi";
	private static final String  PASSWORD = "123";
	private static final String  EMAIL    = "davi@davi.com";
	
	@Mock 
	private ModelMapper mapper;	

	@Mock 
	private UserRepository repository;
	
	@InjectMocks 
	private UserServiceImpl service;
	
	private UserDTO userDto;
	private UserEntity user;
	private Optional<UserEntity> optionalUser;	
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}
	
	@Test
	void WhenFindByIdReturnAnUserInstance() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		
		UserEntity response = service.findById(ID);
		
		assertEquals(UserEntity.class, response.getClass());
	}

	private void startUser() {
		user = new UserEntity(ID, NAME, EMAIL, PASSWORD);
		userDto = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new UserEntity(ID, NAME, EMAIL, PASSWORD));

	}

}
