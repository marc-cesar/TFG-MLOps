package com.backend.mlopsbackend.Repositories;

import com.backend.mlopsbackend.Entities.RetrainingConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@ComponentScan(basePackages = "com.mlopsservice.Services")
public interface RetrainingConfigurationRepository extends JpaRepository<RetrainingConfiguration, Long> {
    RetrainingConfiguration findTopBy();
}
