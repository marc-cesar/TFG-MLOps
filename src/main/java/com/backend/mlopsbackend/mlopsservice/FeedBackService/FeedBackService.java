package com.backend.mlopsbackend.mlopsservice.FeedBackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.backend.mlopsbackend.Entities.Request;
import com.backend.mlopsbackend.Events.NewFeedbackEvent;
import com.backend.mlopsbackend.Services.RequestService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Component
public class FeedBackService {
    // Provide feedback post endpoint, that reads the log db and writes its feedback
    // Send new feedback event when this endpoint is called
    @Autowired
    private RequestService requestService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping(value="/giveFeedback")
    public void predict(@RequestParam Long id, @RequestParam Boolean isCorrect) {
        // Get from database the request with the given id
        Request rest = requestService.getById(id);
        String prediction = rest.getPrediction();
        String feedback;
        if(isCorrect) {
            feedback = prediction;
        } else {
            feedback = prediction.equals("0") ? "1" : "0";
        }
        // Set its feedback
        rest.setFeedback(feedback);
        // Save it to database
        requestService.save(rest);
        // Send new feedback event
        eventPublisher.publishEvent(new NewFeedbackEvent(rest));
    }
}
