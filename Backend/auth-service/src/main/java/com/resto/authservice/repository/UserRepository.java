package com.resto.authservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.resto.authservice.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	Optional<User> findByUserName(String userName);
	Optional<User> findByUserNameAndRole(String userName,String role);
	 @Query("SELECT u FROM User u WHERE u.role IN ('ROLE_ADMIN', 'ROLE_CHEF')")
	    List<User> findAllAdminsAndChefs();

}
