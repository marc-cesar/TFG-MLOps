package com.backend.mlopsbackend.Repositories;

import com.backend.mlopsbackend.Entities.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@ComponentScan(basePackages = "com.mlopsservice.Services")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
