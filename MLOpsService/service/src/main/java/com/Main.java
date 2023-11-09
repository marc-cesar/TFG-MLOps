package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication (scanBasePackages = "com" )
@ComponentScan(basePackages = {"com.Repositories", "com.Services", "com.Entities", "com.Controllers","com.Events","com.mlopsservice","com.Config"})
public class Main {
	

	public static void main(String[] args) {
		startFlaskApp();
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void startFlaskApp() {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "MLOpsService/service/src/main/java/com/mlopsservice/ModelScoringService/ModelScoringService.py");
        // Make sure the environment is set up, if you need to set environment variables you can do so with:
        // processBuilder.environment().put("ENV_VAR", "value");
        
        try {
            Process process = processBuilder.start();

        	// Optionally, you can consume the output/error stream if you don't want to leave it hanging
        	Thread outThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                reader.lines().forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        	});
        	outThread.start();

			Thread errThread = new Thread(() -> {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
					reader.lines().forEach(System.err::println);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			errThread.start();

            System.out.println("Flask application has been started");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}



