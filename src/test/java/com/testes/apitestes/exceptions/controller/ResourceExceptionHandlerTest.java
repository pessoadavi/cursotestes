package com.testes.apitestes.exceptions.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.testes.apitestes.exceptions.ObjectNotFoundException;
import com.testes.apitestes.exceptions.model.StandartErrors;

@SpringBootTest
class ResourceExceptionHandlerTest {

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado.";
	
	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
		ResponseEntity<StandartErrors> response = exceptionHandler
				.ObjectNotFound(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO), new MockHttpServletRequest());
	
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(404, response.getBody().getStatus());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(StandartErrors.class, response.getBody().getClass());
		assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
	}

}
