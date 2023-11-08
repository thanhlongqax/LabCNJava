package com.example.ex01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ex01Application  {

	public static void main(String[] args) {
		SpringApplication.run(Ex01Application.class, args);
	}
	@Bean
	public CommandLineRunner run() {
		return args -> {
			System.out.println("Spring Boot Application is running!");
		};
	}
}
