package com.backend.mlopsbackend.mlopsservice.ModelScoringService;

import com.backend.mlopsbackend.Events.NewRetrainingEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.backend.mlopsbackend.Entities.PredictionResponse;
import com.backend.mlopsbackend.Entities.Request;
import com.backend.mlopsbackend.Events.NewModelEvent;
import com.backend.mlopsbackend.Services.RequestService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;


@RestController
@RequestMapping("/api")
@Component
public class ModelScoringService {

    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final RequestService RequestService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private String PythonServiceURL = "https://tfg-mlops-flask-app-7ceb82f39064.herokuapp.com/";
    
    public ModelScoringService(RestTemplate restTemplate, RequestService requestService) {
        this.restTemplate = restTemplate;
        this.RequestService = requestService;
    }

    @GetMapping(value="/")
    public String HelloWorld(){
        return "Hello World!";
    }

    @PostMapping(value="/predict")
    public ResponseEntity<PredictionResponse> predict(@RequestBody Map<String,List<Integer>> param) {
        // Call python and get prediction*/
        String serviceUrl = PythonServiceURL + "Predict";
        System.err.println("Calling Python API!");
        ResponseEntity<String> prediction = restTemplate.postForEntity(serviceUrl, param, String.class);

        try { // Check if model is loaded
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(prediction.getBody());
            if (rootNode.has("error") && rootNode.get("error").textValue().equals("Model not loaded")){
                eventPublisher.publishEvent(new NewRetrainingEvent());
                return ResponseEntity.ok(new PredictionResponse(null, null, "Model not loaded"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Create the request object and set its prediction
        Request req = new Request();
        req.SetValuesFromMap(param);
        req.setPrediction(prediction.getBody());

        RequestService.save(req);

        PredictionResponse response = new PredictionResponse(prediction.getBody(), req.getId().toString(), null);

        return ResponseEntity.ok(response);
    }

    @EventListener
    public void NewModelListener(NewModelEvent ev) {
        System.out.println("Model reloaded correctly");
        // Call Python to update model
        // String serviceUrl = ServiceDefaultURL + "NewModel";
        // ResponseEntity<Void> response = restTemplate.getForEntity(serviceUrl, Void.class);
        // if(response.getStatusCode() != HttpStatus.OK) {
        //    System.out.println("Error connecting to the API while updating the model");
        // }
    }


}