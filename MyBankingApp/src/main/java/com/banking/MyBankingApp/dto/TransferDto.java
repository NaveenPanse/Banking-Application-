package com.banking.MyBankingApp.dto;

import java.util.UUID;

public class TransferDto {
 public TransferDto(UUID senderAcc, UUID recieverAcc, double amount) {
		super();
		this.senderAcc = senderAcc;
		this.recieverAcc = recieverAcc;
		this.amount = amount;
	}
private UUID senderAcc;
 private UUID recieverAcc;
 private double amount;
public UUID getSenderAcc() {
	return senderAcc;
}
public void setSenderAcc(UUID senderAcc) {
	this.senderAcc = senderAcc;
}
public UUID getRecieverAcc() {
	return recieverAcc;
}
public void setRecieverAcc(UUID recieverAcc) {
	this.recieverAcc = recieverAcc;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
 
}
