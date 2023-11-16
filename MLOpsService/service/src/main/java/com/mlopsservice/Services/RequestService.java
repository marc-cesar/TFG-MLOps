package com.mlopsservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.mlopsservice.Entities.Request;
import com.mlopsservice.Repositories.RequestRepository;

import java.util.List;

@Service
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class RequestService {
    @Autowired(required = true)
    private RequestRepository requestRepository;
    
    public Request getById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public void save(Request request) {
        requestRepository.save(request);
    }
    
}
