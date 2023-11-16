package com.mlopsservice.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackages = "com.mlopsservice.Services")
public class JpaRepositoryConfig {
    // Any additional JPA repository configuration can be added here if needed
}
