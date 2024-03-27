package com.backend.mlopsbackend.mlopsservice.MLModelEngineeringService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.backend.mlopsbackend.Events.DataCollectionReadyEvent;
import com.backend.mlopsbackend.Events.NewModelEvent;

@Component
public class MLModelEngineeringService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @EventListener
    public void DataCollectionReadyListener(DataCollectionReadyEvent ev) {
        ProcessBuilder pb = new ProcessBuilder("python", "Backend/mlopsbackend/src/main/java/com/backend/mlopsbackend/mlopsservice/MLModelEngineeringService/MLModelEngineeringService.py");
        try {
            Process p = pb.start();
            // Wait for the process to exit
            int exitCode = p.waitFor();

            if(exitCode == 0){
                // Send New Model Event
                eventPublisher.publishEvent(new NewModelEvent());

            } else {
                System.out.println("ML model engineering script failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
