package com.testes.apitestes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testes.apitestes.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
		
	List<UserEntity> findAll();
	
	Optional<UserEntity> findByEmail(String email);

}
