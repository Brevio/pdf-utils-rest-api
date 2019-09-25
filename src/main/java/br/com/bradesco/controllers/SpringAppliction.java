package br.com.bradesco.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.bradesco.controllers"})
public class SpringAppliction {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringAppliction.class, args);
		
	}

}
