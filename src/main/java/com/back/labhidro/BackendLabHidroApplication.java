package com.back.labhidro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BackendLabHidroApplication {
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(BackendLabHidroApplication.class, args);
	}

}
