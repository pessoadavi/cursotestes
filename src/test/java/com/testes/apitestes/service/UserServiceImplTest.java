package com.testes.apitestes.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.testes.apitestes.model.dto.UserDTO;
import com.testes.apitestes.model.entity.UserEntity;
import com.testes.apitestes.repository.UserRepository;

@SpringBootTest
class UserServiceImplTest {

	private static final Integer ID       = 1;
	private static final String  NAME 	  = "Davi";
	private static final String  PASSWORD = "123";
	private static final String  EMAIL    = "davi@davi.com";
	
	@MockBean
	private ModelMapper mapper;	

	@MockBean 
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
		
		assertNotNull(response);
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
		assertEquals(UserEntity.class, response.getClass());
		
		
	}

	private void startUser() {
		user = new UserEntity(ID, NAME, EMAIL, PASSWORD);
		userDto = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new UserEntity(ID, NAME, EMAIL, PASSWORD));

	}

}
