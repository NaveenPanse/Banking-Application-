package com.banking.MyBankingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyBankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBankingAppApplication.class, args);
	}

}
