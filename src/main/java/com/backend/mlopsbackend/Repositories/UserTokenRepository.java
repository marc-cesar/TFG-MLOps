package com.backend.mlopsbackend.Repositories;

import com.backend.mlopsbackend.Entities.User;
import com.backend.mlopsbackend.Entities.UserToken;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@ComponentScan(basePackages = "com.mlopsservice.Services")
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM UserToken ut WHERE ut.userId = ?1 AND ut.Token = ?2")
    void deleteUsersTokenWithUserId(Long userid, String token);

    Optional<UserToken> findByToken(String token);
}
