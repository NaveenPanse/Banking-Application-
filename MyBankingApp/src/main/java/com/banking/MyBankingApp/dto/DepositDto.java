package com.banking.MyBankingApp.dto;

import java.util.UUID;

public class DepositDto {
   public DepositDto(UUID accountNo,String username, double amount) {
		super();
		this.accountNo=accountNo;
		this.username = username;
		this.amount = amount;
	}
private String username;
   private double amount;
   private UUID accountNo;
   
   public UUID  getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(UUID accountNo) {
		this.accountNo= accountNo;
	}
	
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
   
}
