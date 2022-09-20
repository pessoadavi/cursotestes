package com.testes.apitestes.exceptions.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StandartErrors {

	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String path;
}
