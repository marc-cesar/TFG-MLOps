package com.backend.mlopsbackend.mlopsservice.ModelScoringService;

import com.backend.mlopsbackend.Entities.Client;
import com.backend.mlopsbackend.Entities.User;
import com.backend.mlopsbackend.Events.NewRetrainingEvent;
import com.backend.mlopsbackend.Repositories.ClientRepository;
import com.backend.mlopsbackend.Services.LogService;
import com.backend.mlopsbackend.Services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.backend.mlopsbackend.Entities.PredictionResponse;
import com.backend.mlopsbackend.Entities.Assessment;
import com.backend.mlopsbackend.Events.NewModelEvent;
import com.backend.mlopsbackend.Services.RequestService;

import java.sql.Timestamp;
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
    private LogService logService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClientRepository clientRepository;
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
    public ResponseEntity<PredictionResponse> predict(@RequestBody Map<String,List<Integer>> param, @RequestParam String token, @RequestParam String dni) {
        // Call python and get prediction*/
        String serviceUrl = PythonServiceURL + "Predict";
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
        Assessment req = new Assessment();
        req.approvalTime = new Timestamp(System.currentTimeMillis());
        req.SetValuesFromMap(param);
        req.setPrediction(prediction.getBody());

        Optional<User> user = userService.getUserFromToken(token);
        user.ifPresent(value -> req.requesterId = value.id);

        // GetClient
        Optional<Client> client = clientRepository.findByDni(dni);
        client.ifPresentOrElse(
                client1 -> req.client = client1,
                () -> {
                    Client clientNew = new Client();
                    clientNew.dni = dni;
                    clientRepository.save(clientNew);
                    req.client = clientNew;
                    req.client.dni = dni;
                }
        );

        RequestService.save(req);

        PredictionResponse response = new PredictionResponse(prediction.getBody(), req.getId().toString(), null);

        return ResponseEntity.ok(response);
    }

    @EventListener
    public void NewModelListener(NewModelEvent ev) {
        logService.Log("New Model Loaded");
    }


}