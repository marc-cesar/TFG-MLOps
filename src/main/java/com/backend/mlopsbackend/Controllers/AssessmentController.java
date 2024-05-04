package com.backend.mlopsbackend.Controllers;

import java.util.List;
import java.util.Optional;

import com.backend.mlopsbackend.Entities.Assessment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.mlopsbackend.Services.RequestService;

@RestController
@RequestMapping("/requests")
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class AssessmentController {
    private final RequestService requestService;
    
    public AssessmentController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value="/all")
    public List<Assessment> getAllRequests(@RequestParam String token){
        if (token != null){
            Optional<List<Assessment>> requests = requestService.getRequestsByToken(token);
            return requests.orElse(null);
        }
        return requestService.getAllRequests();
    }

    @GetMapping(value="/find/{id}")
    public ResponseEntity<Assessment> getRequestById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(requestService.getById(id),HttpStatus.OK);
    }
}
