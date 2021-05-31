package com.fullstackweek.myinvest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyInvestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyInvestAppApplication.class, args);
		System.out.println("Starting project with Spring.");
	}

}
