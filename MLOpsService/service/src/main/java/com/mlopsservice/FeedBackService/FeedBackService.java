package com.mlopsservice.FeedBackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entities.Request;
import com.Events.NewFeedbackEvent;
import com.Services.RequestService;

@CrossOrigin(origins = "http://localhost:4200")
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
    public void predict(@RequestParam Long id, @RequestParam String feedback) {
        // Get from database the request with the given id
        Request rest = requestService.getById(id);
        // Set its feedback
        rest.setFeedback(feedback);
        // Save it to database
        requestService.save(rest);
        // Send new feedback event
        eventPublisher.publishEvent(new NewFeedbackEvent(rest));
    }

}
