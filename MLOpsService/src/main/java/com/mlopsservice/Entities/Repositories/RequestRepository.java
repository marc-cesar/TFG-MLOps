package com.mlopsservice.Entities.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mlopsservice.Entities.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
    
}
