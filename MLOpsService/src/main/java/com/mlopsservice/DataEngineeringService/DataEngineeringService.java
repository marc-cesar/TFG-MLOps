package com.mlopsservice.DataEngineeringService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mlopsservice.Events.DataCollectionReadyEvent;
import com.mlopsservice.Events.NewRetrainingEvent;


@Component
public class DataEngineeringService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @EventListener
    public void NewRetrainingListener(NewRetrainingEvent ev) {
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
