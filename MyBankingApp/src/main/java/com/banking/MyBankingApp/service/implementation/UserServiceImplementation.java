// User Service Implementation
package com.banking.MyBankingApp.service.implementation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.MyBankingApp.dto.DepositDto;
import com.banking.MyBankingApp.dto.TransferDto;
import com.banking.MyBankingApp.dto.UserDto;
import com.banking.MyBankingApp.dto.WithdrawDto;
import com.banking.MyBankingApp.entity.User;
import com.banking.MyBankingApp.repository.UserRepository;
import com.banking.MyBankingApp.service.UserService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
   
    //constructor
    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
       
    }

    //method to register new user
    @Override
	public void registerUser(User Reguser) {//Reguser-> registering user
    	User user = Reguser;
    	
        user.setPassword(passwordEncoder.encode(Reguser.getPassword()));
        userRepository.save(user);
        
		
	}

    //method to find user by name
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    
    //method to authenticate user
	@Override
	public boolean authenticate(UserDto userDto) {
		
		 User user = userRepository.findById(userDto.getId())
	                .orElseThrow(() -> new RuntimeException("User not found!"));
		
		 if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
		        return true; // Password matches
		    } else {
		        throw new RuntimeException("Invalid credentials!");
		    }
		
	}

	
	// method to deposit amount
	@Override
	public void depositAmount(DepositDto depositDto) {
		 User user = userRepository.findById(depositDto.getAccountNo())
	                .orElseThrow(() -> new RuntimeException("User not found!"));
    	double total=user.getBalance() + depositDto.getAmount();
    	user.setBalance(total);
		userRepository.save(user);
	}

	
	
	//method to transfer money from sender to reciever
	@Override
	public void transferAmount(TransferDto transferDto) {
		 User sender = userRepository.findById(transferDto.getSenderAcc())
	                .orElseThrow(() -> new RuntimeException("Sender not found!"));
		
		 User reciever = userRepository.findById(transferDto.getRecieverAcc())
	                .orElseThrow(() -> new RuntimeException("Reciever not found!"));
		 
		 if(sender.getBalance()<transferDto.getAmount()) {
			 throw  new RuntimeException("Insufficient Balance!");
		 }
		 else {
			 sender.setBalance(sender.getBalance()-transferDto.getAmount());    //money debited
			 userRepository.save(sender);
			 double amount=reciever.getBalance() + transferDto.getAmount(); 
			 reciever.setBalance(amount);     //money credited
			 userRepository.save(reciever);
			 
		 }
	}

	
	//method to find user by UUID(Universally Unique Identifier)
	@Override
	public User findById(UUID id) {
		User user=userRepository.findById(id).orElse(null);
		return user;
	}

	
	
	//method to send SMS to user's mobile number using Twilio API
	@Override
	public void sendSms(String to, String message) {
		 final String accountSid= "my_accountSid";
		 final String authToken="authenticaion_token";
		 final String fromNumber="twilio_number";
		 
		 Twilio.init(accountSid, authToken);
		 if (!to.startsWith("+")) {
		        to = "+91" + to; // Assuming the country code of India (+91)
		    }
	        Message.creator(
	                new PhoneNumber(to),
	                new PhoneNumber(fromNumber),
	                message
	        ).create();
	}

	@Override
	public void withdrawAmount(WithdrawDto withdrawDto) {
		 User user = userRepository.findById(withdrawDto.getAccountNo())
	                .orElseThrow(() -> new RuntimeException("User not found!"));
		 if(user.getBalance()<withdrawDto.getAmount()) {
			 throw  new RuntimeException("Insufficient Balance!");
		 }
		 else {
			 user.setBalance(user.getBalance()-withdrawDto.getAmount());    //money debited
			 userRepository.save(user);
			}
		
	}

	@Override
	public void deleteById(UUID id) {
		userRepository.deleteById(id);
		
	}

	
}
