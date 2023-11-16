package com.mlopsservice.Repositories;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mlopsservice.Entities.Request;


@Repository
@ComponentScan(basePackages = "com.mlopsservice.Services")
public interface RequestRepository extends JpaRepository<Request, Long> {
    
}
