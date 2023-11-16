package com.mlopsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication (scanBasePackages = "com" )
@EntityScan(basePackages = {"Entities"} )
//@EnableJpaRepositories(basePackages = {"com.mlopsservice.Repositories"})
@ComponentScan(basePackages = {"com.mlopsservice.Services", 
	"com.mlopsservice.Controllers",
	"com.mlopsservice.Events",
	"com.mlopsservice.mlopsservice",
	"com.mlopsservice.Config",
	"com.mlopsservice.Services"})
public class Main {
	

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}



