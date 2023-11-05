package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication (scanBasePackages = "com" )
@ComponentScan(basePackages = {"com.Repositories", "com.Services", "com.Entities", "com.Controllers","com.Events","com.mlopsservice"})
public class Main {
	

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}



