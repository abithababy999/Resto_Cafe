package com.resto.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resto.authservice.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	Optional<User> findByUserName(String userName);
	Optional<User> findByUserNameAndRole(String userName,String Role);

}
