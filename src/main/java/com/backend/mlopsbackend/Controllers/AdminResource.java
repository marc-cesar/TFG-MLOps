package com.backend.mlopsbackend.Controllers;

import com.backend.mlopsbackend.Events.NewRetrainingEvent;
import com.backend.mlopsbackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class AdminResource {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired(required = true)
    private UserService userService;

    @PostMapping("/forceRetraining")
    public ResponseEntity<Map<String, String>> forceRetraining(@RequestParam String token){
        if (userService.isUserAdmin(token)){
            eventPublisher.publishEvent(new NewRetrainingEvent());
            return ResponseEntity.ok(Collections.singletonMap("message", "Retraining process finished successfully. Check logs for more details"));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access denied. User must be an admin"));
    }
}
