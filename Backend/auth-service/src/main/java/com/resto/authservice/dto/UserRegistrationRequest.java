package com.resto.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserRegistrationRequest {
	    private String userName;
        private String firstName;
	    private String lastName;
	    private String password;
	    private String phoneNumber;
	    
	    public UserRegistrationRequest() {
	    	
	    }
		public UserRegistrationRequest(String userName, String firstName, String lastName, String password,
				String phoneNumber) {
			super();
			this.userName = userName;
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;
			this.phoneNumber = phoneNumber;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		

}
