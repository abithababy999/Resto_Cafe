package com.resto.inventoryservice.dto;



public class UserResponse {
	
	private long userid;

	private String userName;
    private String firstName;
	private String lastName;
	private String phoneNumber;
    private String role;
    
    public UserResponse() {
    	
    }
	public UserResponse(long userid, String userName, String firstName, String lastName, String phoneNumber,
			String role) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
}
