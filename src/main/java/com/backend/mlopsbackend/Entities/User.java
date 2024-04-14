package com.backend.mlopsbackend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true)
    public String username;
    public String encryptedPassword;
    public Boolean isAdmin = false;

    public User(){}

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
