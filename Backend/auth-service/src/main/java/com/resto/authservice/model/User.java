package com.resto.authservice.model;



import javax.persistence.*;


import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userid;
	@Column(unique = true)
	private String userName;
  
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String password;
    private String role;

}
