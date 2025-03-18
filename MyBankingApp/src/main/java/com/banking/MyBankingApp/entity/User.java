package com.banking.MyBankingApp.entity;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	public User() {
		
	}
    public User(UUID id,String number,String email,double balance, String username, String password) {
		super();
		this.balance=balance;
		this.id=id;
		this.number=number;
		this.email = email;
		this.username = username;
		this.password = password;
		
	}
	@Id
	 @GeneratedValue
	 //UUID --> Universally Unique Identifier
	private UUID id;
   private String number;
    private String email;
private double balance;
    private String username;
    private String password;
	
    
    // Getters and Setters
    
    
    public String getEmail() {
		return email;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return id;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumber() {
		return number;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getBalance() {
		return balance;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    // Getters and Setters
    
  }