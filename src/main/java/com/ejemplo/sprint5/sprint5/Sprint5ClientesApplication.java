package com.ejemplo.sprint5.sprint5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Sprint5ClientesApplication
{
	public static void main(String[] args) {
		SpringApplication.run(Sprint5ClientesApplication.class, args);
	}
}
