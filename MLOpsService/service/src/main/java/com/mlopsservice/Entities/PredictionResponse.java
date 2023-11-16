package com.mlopsservice.Entities;

public class PredictionResponse {
    public String prediction;
    public String id;

    public PredictionResponse(String prediction, String id) {
        this.prediction = prediction;
        this.id = id;
    }
}
