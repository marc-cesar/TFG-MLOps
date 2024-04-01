package com.backend.mlopsbackend.mlopsservice.ModelMonitoringService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.backend.mlopsbackend.Entities.Request;
import com.backend.mlopsbackend.Events.NewFeedbackEvent;
import com.backend.mlopsbackend.Events.NewRetrainingEvent;


@Component
public class ModelMonitoringService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private int correctPredictions = 0;
    private int incorrectPredictions = 0;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        // On init, publish a new retraining event so that the scoring service loads the model
        eventPublisher.publishEvent(new NewRetrainingEvent());
    }



    @EventListener
    public void NewFeedbackListener(NewFeedbackEvent ev){
        // Get the request from the database
        Request rest = ev.getRequest();
        if(rest.getPrediction().equals(rest.getFeedback())){
            correctPredictions++;
        } else {
            incorrectPredictions++;
        }

        // Condition to be changed
        if(correctPredictions + incorrectPredictions == 10){
            // Send event to retrain the model
                eventPublisher.publishEvent(new NewRetrainingEvent());
                correctPredictions = 0;
                incorrectPredictions = 0;
        }
    }
}
