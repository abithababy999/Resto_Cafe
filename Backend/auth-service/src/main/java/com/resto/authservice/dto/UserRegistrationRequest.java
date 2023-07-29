package com.resto.authservice.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {
	    private String userName;
        private String firstName;
	    private String lastName;
	    private String password;
	    private String phoneNumber;

}
