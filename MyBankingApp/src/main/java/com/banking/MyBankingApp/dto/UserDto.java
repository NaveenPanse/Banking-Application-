package com.banking.MyBankingApp.dto;

import java.util.UUID;

public class UserDto {

	
	private String password;
	private UUID id;
	
	//constructor
	public UserDto( String password, UUID id) {
		super();
		
		
		this.password = password;
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	} 
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	
}
