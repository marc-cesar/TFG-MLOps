package com.backend.mlopsbackend.Repositories;

import com.backend.mlopsbackend.Entities.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.mlopsbackend.Entities.Request;

import java.util.List;
import java.util.Optional;


@Repository
@ComponentScan(basePackages = "com.mlopsservice.Services")
public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<List<Request>> findAllByRequesterId(Long requesterId);
}
