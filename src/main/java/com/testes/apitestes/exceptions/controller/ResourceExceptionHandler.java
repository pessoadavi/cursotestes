package com.testes.apitestes.exceptions.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.testes.apitestes.exceptions.DataIntegrityViolationException;
import com.testes.apitestes.exceptions.ObjectNotFoundException;
import com.testes.apitestes.exceptions.model.StandartErrors;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartErrors> ObjectNotFound(ObjectNotFoundException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body(new StandartErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandartErrors> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(new StandartErrors(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI()));
	}
}
