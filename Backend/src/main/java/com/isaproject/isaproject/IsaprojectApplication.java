package com.isaproject.isaproject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class IsaprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaprojectApplication.class, args);
	}
}