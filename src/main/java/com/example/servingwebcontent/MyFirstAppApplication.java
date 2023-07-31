package com.example.servingwebcontent;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class MyFirstAppApplication {

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	public static void main(String[] args) {
		SpringApplication.run(MyFirstAppApplication.class, args);
	}

	@Bean
	public OpenAPI baseOpenApi()
	{
		return new OpenAPI().info(new Info()
				.title("Ticket shop")
				.version("1.0.0")
				.description("Ticket shop REST API"));
	}


}
