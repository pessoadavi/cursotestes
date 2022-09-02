package com.testes.apitestes.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.testes.apitestes.exceptions.DataIntegrityViolationException;
import com.testes.apitestes.exceptions.ObjectNotFoundException;
import com.testes.apitestes.model.dto.UserDTO;
import com.testes.apitestes.model.entity.UserEntity;
import com.testes.apitestes.repository.UserRepository;

@SpringBootTest
class UserServiceImplTest {
	
	private static final Integer INDEX	  = 0;
	private static final Integer ID       = 1;
	private static final String  NAME 	  = "Davi";
	private static final String  PASSWORD = "123";
	private static final String  EMAIL    = "davi@davi.com";
	private static final String  MESSAGE  = "Usuário não encontrado.";
	
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
	
	@Test
	void WhenFindByIdReturnAnObjectNotFoundException() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(MESSAGE));
		
		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals("Usuário não encontrado.", e.getMessage());
			assertEquals(ObjectNotFoundException.class, e.getClass());
		}
	}
	
	@Test
	void WhenFindAllReturnAnListOfUsers() {
		Mockito.when(repository.findAll()).thenReturn(List.of(user));
		
		List<UserEntity> response = service.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(ID, response.get(INDEX).getId());
		assertEquals(NAME, response.get(INDEX).getName());
		assertEquals(EMAIL, response.get(INDEX).getEmail());
		assertEquals(PASSWORD, response.get(INDEX).getPassword());
		assertEquals(UserEntity.class, response.get(INDEX).getClass());
	}
	
	@Test
	void WhenCreateUserThenReturnSuccess() {
		Mockito.when(repository.saveAndFlush(Mockito.any())).thenReturn(user);
		
		UserEntity response = service.save(userDto);
		
		assertNotNull(response);
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
		assertEquals(UserEntity.class, response.getClass());
		
		
	}
	
	@Test
	void WhenCreateUserThenReturnAnDataIntegrityViolationException() {
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2);
			service.save(userDto);	
		} catch (Exception e) {
			assertEquals(DataIntegrityViolationException.class, e.getClass());
			assertEquals("E-mail já cadastro. Informe um diferente.", e.getMessage());
		}		
	}
	
	@Test
	void WhenUpdateUserThenReturnSuccess() {
		Mockito.when(repository.saveAndFlush(Mockito.any())).thenReturn(user);
		
		UserEntity response = service.update(userDto);
		
		assertNotNull(response);
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(PASSWORD, response.getPassword());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(UserEntity.class, response.getClass());
	}
	
	@Test
	void WhenUpdateUserThenReturnAnDataIntegrityViolationException() {
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2);
			service.save(userDto);	
		} catch (Exception e) {
			assertEquals(DataIntegrityViolationException.class, e.getClass());
			assertEquals("E-mail já cadastro. Informe um diferente.", e.getMessage());
		}		
	}
	
	@Test
	void deleteWithiSuccess() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		Mockito.doNothing().when(repository).deleteById(Mockito.anyInt());
		service.delete(ID);
		Mockito.verify(repository, times(1)).deleteById(Mockito.anyInt());
	}
	
	@Test
	void deleteWithObjectNotFoundException() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(MESSAGE));
		try {
			service.delete(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals(MESSAGE, e.getMessage());
		}
	}
	
	private void startUser() {
		user = new UserEntity(ID, NAME, EMAIL, PASSWORD);
		userDto = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new UserEntity(ID, NAME, EMAIL, PASSWORD));

	}

}
