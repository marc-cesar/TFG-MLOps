package com.backend.mlopsbackend.Repositories;

import com.backend.mlopsbackend.Entities.ServiceLog;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@ComponentScan(basePackages = "com.mlopsservice.Services")
public interface LogRepository extends JpaRepository<ServiceLog, Long> {
    @Query("SELECT y FROM ServiceLog y ORDER BY y.date DESC")
    List<ServiceLog> findAllOrderByDateDesc();
}
