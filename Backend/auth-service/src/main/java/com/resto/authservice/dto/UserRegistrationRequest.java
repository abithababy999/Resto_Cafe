package com.resto.authservice.dto;



public class UserRegistrationRequest {
	    private String userName;
        private String firstName;
	    private String lastName;
	    private String userPassword;
	    private String phoneNumber;
	    
	    public UserRegistrationRequest() {
	    	
	    }
		public UserRegistrationRequest(String userName, String firstName, String lastName, String userPassword,
				String phoneNumber) {
			super();
			this.userName = userName;
			this.firstName = firstName;
			this.lastName = lastName;
			this.userPassword = userPassword;
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
		public String getUserPassword() {
			return userPassword;
		}
		public void setUserPassword(String password) {
			this.userPassword = password;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		

}
