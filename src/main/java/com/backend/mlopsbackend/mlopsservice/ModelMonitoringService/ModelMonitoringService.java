package com.backend.mlopsbackend.mlopsservice.ModelMonitoringService;

import com.backend.mlopsbackend.Entities.RetrainingConfiguration;
import com.backend.mlopsbackend.Repositories.RetrainingConfigurationRepository;
import com.backend.mlopsbackend.Services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.backend.mlopsbackend.Entities.Assessment;
import com.backend.mlopsbackend.Events.NewFeedbackEvent;
import com.backend.mlopsbackend.Events.NewRetrainingEvent;


@Component
public class ModelMonitoringService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private RetrainingConfigurationRepository retrainingConfigurationRepository;

    @Autowired
    private LogService logService;

    private int correctPredictions = 0;
    private int incorrectPredictions = 0;
    
    @EventListener
    public void NewFeedbackListener(NewFeedbackEvent ev){
        // Get the request from the database
        Assessment rest = ev.getRequest();
        if(rest.getPrediction().equals(rest.getFeedback())){
            correctPredictions++;
        } else {
            incorrectPredictions++;
        }

        if(NeedsRetraining()){
            // Send event to retrain the model
            eventPublisher.publishEvent(new NewRetrainingEvent());
            correctPredictions = 0;
            incorrectPredictions = 0;
        }
    }

    public Boolean NeedsRetraining(){
        // Get the entity
        RetrainingConfiguration config = this.retrainingConfigurationRepository.findTopBy();
        if (config == null){
           // Create the config instance for the first time
           config = new RetrainingConfiguration();
           config.setMinimumRequests(10);
           config.setSuccessPercentage(60);
           retrainingConfigurationRepository.save(config);
        }
        return (correctPredictions * 100) / (incorrectPredictions + correctPredictions) < config.getSuccessPercentage()
                && correctPredictions + incorrectPredictions > config.getMinimumRequests();
    }
}
