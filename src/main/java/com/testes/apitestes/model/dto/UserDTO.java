package com.testes.apitestes.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable{

	private static final long serialVersionUID = -4524779261772033050L;
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
}
