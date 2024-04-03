package com.backend.mlopsbackend.mlopsservice.MLModelEngineeringService;

import com.backend.mlopsbackend.Helpers.PythonScriptExecutor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.backend.mlopsbackend.Events.DataCollectionReadyEvent;
import com.backend.mlopsbackend.Events.NewModelEvent;

import java.io.File;

@Component
public class MLModelEngineeringService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @EventListener
    public void DataCollectionReadyListener(DataCollectionReadyEvent ev) {
        PythonScriptExecutor executor = new PythonScriptExecutor();
        try {
            File scriptFile = executor.ExtractFileFromResources("scripts/MLModelEngineeringService.py");
            Pair<Integer,String> returnCode = executor.ExecutePythonScript(scriptFile, null, ev.dataPath, false,false);
            // Exitcode is returncode.a (the first element of the pair)
            if(returnCode.a == 0) // Executed Correctly
                // Send Data Collection Ready Event
                eventPublisher.publishEvent(new NewModelEvent());
            else
                System.out.println("ML Model Engineering script failed");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
}
