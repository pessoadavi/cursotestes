package com.testes.apitestes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.testes.apitestes.model.dto.UserDTO;
import com.testes.apitestes.model.entity.UserEntity;
import com.testes.apitestes.service.UserService;

@SpringBootTest
class UserControllerTest {

	private static final Integer INDEX	  = 0;
	private static final Integer ID       = 1;
	private static final String  NAME 	  = "Davi";
	private static final String  PASSWORD = "123";
	private static final String  EMAIL    = "davi@davi.com";
	private static final String  MESSAGE  = "Usuário não encontrado.";
	
	@MockBean
	private ModelMapper mapper;
	
	@MockBean
	private UserService service;
	
	@InjectMocks
	UserController controller;
	
	private UserDTO userDto;
	private UserEntity user;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}
	
	@Test
	void WhenFindByIdThenReturnSuccess() {
		when(service.findById(anyInt())).thenReturn(user);
		when(mapper.map(any(), any())).thenReturn(userDto);
		
		ResponseEntity<UserDTO> response = controller.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UserDTO.class, response.getBody().getClass());
		
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(PASSWORD, response.getBody().getPassword());
	}
	
	private void startUser() {
		user = new UserEntity(ID, NAME, EMAIL, PASSWORD);
		userDto = new UserDTO(ID, NAME, EMAIL, PASSWORD);
	}

}
