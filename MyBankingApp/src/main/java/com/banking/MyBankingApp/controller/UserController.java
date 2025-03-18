package com.banking.MyBankingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.banking.MyBankingApp.dto.DepositDto;
import com.banking.MyBankingApp.dto.TransferDto;
import com.banking.MyBankingApp.dto.UserDto;
import com.banking.MyBankingApp.dto.WithdrawDto;
import com.banking.MyBankingApp.entity.User;
import com.banking.MyBankingApp.service.UserService;
//import com.in28minutes.springboot.myfirstwebapp.todo.Todo;

@Controller

public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // first page
    @RequestMapping(value="/" ,method=RequestMethod.GET)
    public String mainPage() {
        return "index";
    }
    
    // User Login
    @RequestMapping(value="/login-Page" ,method=RequestMethod.GET)
    public String loginPage() {
        return "login_Page";
    }
    
    //post mapping that return logged in page for authenticated user
    @RequestMapping(value="/loggedInPage" ,method=RequestMethod.POST)
    public String loggedInPage(@ModelAttribute UserDto userDto, ModelMap model) {
    	try {
    		
    	boolean isAuthenticated = userService.authenticate(userDto);
            
         	User user = userService.findById(userDto.getId());
             model.addAttribute("user",user);
            userService.sendSms(user.getNumber(),"You have Successfully Logged in on MyBankingApp!");
             return "loggedIn";
       
    	}
    	 catch (RuntimeException e) {
    		
    	        model.addAttribute("error", e.getMessage());
    	        return "login_Page"; // Show login page with error from exception
    	 }
    }
    
 
    // User Registration Page
    @RequestMapping(value="/register",method=RequestMethod.GET)
    public String registerUserForm() {
       return "registrationPage";
    }
    
   //post mapping for registering user 
    @PostMapping("/register")
    public String registerUser( User user ,ModelMap model) {
    	
        userService.registerUser(user);
        userService.sendSms(user.getNumber(),"You Registered Successfully! on MyBankingApp, and your Account number is : " + user.getId()+".");
        model.put("registered","You Registered Successfully! ");
        return "login_Page";
    }

   //Deposit money Page
    @RequestMapping(value="/deposit" ,method=RequestMethod.GET)
    public String depositPage() {
    	
        return "depositPage";
    }
    
    
    //Post mapping for depositing money
    @RequestMapping(value="/deposit" ,method=RequestMethod.POST)
    public String deposit(@ModelAttribute DepositDto depositDto , ModelMap model) {
    	User user=userService.findById(depositDto.getAccountNo());
    	userService.depositAmount(depositDto);
    	userService.sendSms(user.getNumber(),"Dear MyBankingApp user, Amount : "+depositDto.getAmount() + " INR credited to your Account. ");
    	model.put("deposited", "Money Deposited Successfully!");
    	return "login_Page";
    }
   
    
    //Withdraw money Page
    @RequestMapping(value="/withdraw" ,method=RequestMethod.GET)
    public String withdrawPage() {
    	
        return "withdrawPage";
    }
    
    
  //Post mapping for withdrawing money
   @RequestMapping(value="/withdraw" ,method=RequestMethod.POST)
    public String withdraw(@ModelAttribute WithdrawDto withdrawDto , ModelMap model) {
	   
	   try {
    	User user=userService.findById(withdrawDto.getAccountNo());
    	userService.withdrawAmount(withdrawDto);
    	 userService.sendSms(user.getNumber(),"Dear MyBankingApp user, Amount : "+withdrawDto.getAmount() + " INR debited from your Account. ");
    	model.put("Withdraw", "Money Withdrawn Successfully!");
    	return "login_Page";
	   }
    	 catch (RuntimeException e) {
 	        model.addAttribute("error", e.getMessage());
 	        return "login_Page"; // Show login page with error from exception
 	 }
    }
    
    //Transfer money page
    @RequestMapping(value="/transfer" ,method=RequestMethod.GET)
    public String transferPage() {
    	
        return "transferPage";
    }
    
    
    //post mapping for transfering money
    @RequestMapping(value="/transfer" ,method=RequestMethod.POST)
    public String transfer(@ModelAttribute TransferDto transferDto , ModelMap model) {
    	User sender=userService.findById(transferDto.getSenderAcc());
    	User reciever=userService.findById(transferDto.getRecieverAcc());
    	
    	userService.transferAmount(transferDto);
    	 userService.sendSms(sender.getNumber(),"Dear MyBankingApp user, Amount : "+transferDto.getAmount() + " INR debited from your Account to Account number: "+transferDto.getRecieverAcc());
    	 userService.sendSms(reciever.getNumber(),"Dear MyBankingApp user, Amount : " +transferDto.getAmount() + " INR credited to your Account by Account number:  " + transferDto.getSenderAcc());
    	model.put("transfered", "Money Transfered Successfully!");
    	return "login_Page";
    }
    
  //Delete account page
    @RequestMapping(value="/delete" ,method=RequestMethod.GET)
    public String deletePage() {
    	
        return "deletePage";
    }
    
  //post mapping for deleting account
    @RequestMapping(value="/delete" ,method=RequestMethod.POST)
    public String deleteAccount(@ModelAttribute UserDto userDto, ModelMap model) {
    	try {
    		
    	boolean isAuthenticated = userService.authenticate(userDto);
            
         	User user = userService.findById(userDto.getId());
         	userService.deleteById(user.getId());
         	model.put("deleted", "Account Deleted Successfully!");
            userService.sendSms(user.getNumber(),"Your MyBankingApp account has been Successfully deleted!");
             return "login_Page";
       
    	}
    	 catch (RuntimeException e) {
    		
    	        model.addAttribute("error", e.getMessage());
    	        return "login_Page"; // Show login page with error from exception
    	 }
    }
    
 
}
