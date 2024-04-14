package com.backend.mlopsbackend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="retrainingconfig", schema = "public")
public class RetrainingConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int successPercentage;

    private int minimumRequests;

    public void setSuccessPercentage(int percentage){
        this.successPercentage = percentage;
    }

    public int getSuccessPercentage(){
        return successPercentage;
    }

    public void setMinimumRequests(int requests){
        this.minimumRequests = requests;
    }

    public int getMinimumRequests(){
        return minimumRequests;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
