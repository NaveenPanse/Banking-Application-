package com.banking.MyBankingApp.dto;

import java.util.UUID;

public class WithdrawDto {

	public WithdrawDto( UUID accountNo,String username, Long amount) {
		super();
		this.username = username;
		this.amount = amount;
		this.accountNo=accountNo; 
	}
	private UUID accountNo;
	private String username;
	private Long amount;
	
	public UUID getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(UUID accountNo) {
		this.accountNo = accountNo;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	
}
