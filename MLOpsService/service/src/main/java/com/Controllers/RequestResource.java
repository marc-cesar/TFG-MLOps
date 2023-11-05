package com.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entities.Request;
import com.Services.RequestService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/requests")
public class RequestResource {
    private final RequestService requestService;
    
    public RequestResource(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value="/all")
    public ResponseEntity<List<Request>> getAllRequests() {
        return new ResponseEntity<>(requestService.getAllRequests(),HttpStatus.OK);
    }

    @GetMapping(value="/find/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(requestService.getById(id),HttpStatus.OK);
    }

    /* @PostMapping(value="/add")
    public ResponseEntity<Request> addRequest(Request request) {
        Request newRequest = requestService.save(request);
        return new ResponseEntity<>(newRequest,HttpStatus.CREATED);
    }*/
}
