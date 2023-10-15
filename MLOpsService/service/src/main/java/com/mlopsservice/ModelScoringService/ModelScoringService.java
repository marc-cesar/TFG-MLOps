package com.mlopsservice.ModelScoringService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mlopsservice.Entities.Request;
import com.mlopsservice.Entities.Repositories.RequestRepository;

import java.util.*;

//import com.mlopsservice.Data.Entities.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ModelScoringService {

    private final RestTemplate restTemplate;
    private final RequestRepository RequestRepository;
    
    @Autowired
    public ModelScoringService(RestTemplate restTemplate, RequestRepository RequestRepository) {
        this.restTemplate = restTemplate;
        this.RequestRepository = RequestRepository;
    }

    @PostMapping(value="/predict")
    public String predict(@RequestBody Map<String,List<Integer>> param) {
        // Call python and get prediction*/
        String serviceUrl = "http://127.0.0.1:5000/Predict";
        ResponseEntity<String> prediction = restTemplate.postForEntity(serviceUrl, param, String.class);
        // Create the request object and set its prediction
        Request req = new Request();
        req.SetValuesFromMap(param);
        req.setPrediction(prediction.getBody());

        RequestRepository.save(req);
        // Write log into database and return id
        return "prediction is " + prediction.getBody() + " and id is " + req.getId().toString();
    }
    
    // Consume Event



}