package com.backend.mlopsbackend.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clients", schema = "public")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto generate id
    public Long id;

    @Column(unique = true)
    public String dni;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @JsonBackReference
    public List<Assessment> assessments = new ArrayList<>();
}
