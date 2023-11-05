package com.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entities.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
    
}
