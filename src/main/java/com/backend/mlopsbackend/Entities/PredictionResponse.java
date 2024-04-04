package com.backend.mlopsbackend.Entities;

public class PredictionResponse {
    public String prediction;
    public String id;
    public String errorMesage;

    public PredictionResponse(String prediction, String id, String errorMessage) {
        this.prediction = prediction;
        this.id = id;
        this.errorMesage = errorMessage;
    }
}
