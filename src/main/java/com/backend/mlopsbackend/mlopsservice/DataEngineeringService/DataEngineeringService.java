package com.backend.mlopsbackend.mlopsservice.DataEngineeringService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.backend.mlopsbackend.Events.DataCollectionReadyEvent;
import com.backend.mlopsbackend.Events.NewRetrainingEvent;


@Component
public class DataEngineeringService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @EventListener
    public void NewRetrainingListener(NewRetrainingEvent ev) {
        // Print working directory
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        ProcessBuilder pb = new ProcessBuilder("python", "MLOpsService/service/src/main/java/com/mlopsservice/DataEngineeringService/DataEngineeringService.py");
        
        try {
            Process p = pb.start();
            // Wait for the process to exit
            int exitCode = p.waitFor();

            if(exitCode == 0){
                // Send Data Collection Ready Event
                eventPublisher.publishEvent(new DataCollectionReadyEvent());

            } else {
                System.out.println("Data engineering script failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

