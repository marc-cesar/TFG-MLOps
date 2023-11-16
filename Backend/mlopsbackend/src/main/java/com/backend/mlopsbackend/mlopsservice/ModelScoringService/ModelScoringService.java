package com.backend.mlopsbackend.mlopsservice.ModelScoringService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.backend.mlopsbackend.Entities.PredictionResponse;
import com.backend.mlopsbackend.Entities.Request;
import com.backend.mlopsbackend.Events.NewModelEvent;
import com.backend.mlopsbackend.Services.RequestService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.context.event.EventListener;



@RestController
@RequestMapping("/api")
@Component
public class ModelScoringService {

    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final RequestService RequestService;

    private String ServiceDefaultURL = "http://127.0.0.1:5000/";
    
    public ModelScoringService(RestTemplate restTemplate, RequestService requestService) {
        this.restTemplate = restTemplate;
        this.RequestService = requestService;
    }

    @PostMapping(value="/predict")
    public ResponseEntity<PredictionResponse> predict(@RequestBody Map<String,List<Integer>> param) {
        // Call python and get prediction*/
        String serviceUrl = ServiceDefaultURL + "Predict";
        ResponseEntity<String> prediction = restTemplate.postForEntity(serviceUrl, param, String.class);
        // Create the request object and set its prediction
        Request req = new Request();
        req.SetValuesFromMap(param);
        req.setPrediction(prediction.getBody());

        RequestService.save(req);

        PredictionResponse response = new PredictionResponse(prediction.getBody(), req.getId().toString());

        return ResponseEntity.ok(response);
    }
    
    @EventListener
    public void NewModelListener(NewModelEvent ev) {
        // Call Python to update model
        String serviceUrl = ServiceDefaultURL + "NewModel";
        ResponseEntity<Void> response = restTemplate.getForEntity(serviceUrl, Void.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            System.out.println("Error connecting to the API while updating the model");
        }
    }


}