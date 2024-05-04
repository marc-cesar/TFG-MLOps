package com.backend.mlopsbackend.Repositories;

import com.backend.mlopsbackend.Entities.Assessment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@ComponentScan(basePackages = "com.mlopsservice.Services")
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    Optional<List<Assessment>> findAllByRequesterId(Long requesterId);
}
