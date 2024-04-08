package com.backend.mlopsbackend.Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="logs", schema = "public")
public class ServiceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Temporal(TemporalType.TIMESTAMP)
    public Date date;

    @Column(length = 1024)
    public String log;

    public ServiceLog(){}

    @PrePersist
    protected void onCreate() {
        date = new Date(); // Sets the date field to the current date and time before persisting
    }
}
