package com.backend.mlopsbackend.Repositories;

import com.backend.mlopsbackend.Entities.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@ComponentScan(basePackages = "com.mlopsservice.Services")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<List<User>> findUsersByIsAdminTrueOrderById();
}
