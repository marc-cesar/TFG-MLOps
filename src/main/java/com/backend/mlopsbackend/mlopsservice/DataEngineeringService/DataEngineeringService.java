package com.backend.mlopsbackend.mlopsservice.DataEngineeringService;

import com.backend.mlopsbackend.Helpers.PythonScriptExecutor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.backend.mlopsbackend.Events.DataCollectionReadyEvent;
import com.backend.mlopsbackend.Events.NewRetrainingEvent;

import java.io.File;


@Component
public class DataEngineeringService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @EventListener
    public void NewRetrainingListener(NewRetrainingEvent ev) throws Exception {
        PythonScriptExecutor executor = new PythonScriptExecutor();
        try {
            File scriptFile = executor.ExtractFileFromResources("scripts/DataEngineeringService.py");
            File dataFile = executor.ExtractFileFromResources("data/german.data");
            Pair<Integer,String> returnPair = executor.ExecutePythonScript(scriptFile,dataFile,null, true,true);
            // ExitCode, output
            if (returnPair.a == 0) // Executed correctly
                // Send Data Collection Ready Event
                eventPublisher.publishEvent(new DataCollectionReadyEvent(returnPair.b));
            else
                System.out.println("Data engineering script failed: " + returnPair.b);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

