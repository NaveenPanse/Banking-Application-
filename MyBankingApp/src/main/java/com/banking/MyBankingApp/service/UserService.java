// User Service
package com.banking.MyBankingApp.service;

import java.util.UUID;

import com.banking.MyBankingApp.dto.DepositDto;
import com.banking.MyBankingApp.dto.TransferDto;
import com.banking.MyBankingApp.dto.UserDto;
import com.banking.MyBankingApp.dto.WithdrawDto;
import com.banking.MyBankingApp.entity.User;


public interface UserService {
    void registerUser(User user);
    User findByUsername(String username);
	boolean authenticate(UserDto userDto);
	void depositAmount(DepositDto depositDto);
	void transferAmount(TransferDto transferDto);
	User findById(UUID id);
	void sendSms(String number, String string);
	void withdrawAmount(WithdrawDto withdrawDto);
	void deleteById(UUID id);
}