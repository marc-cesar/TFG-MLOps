package com.mlopsservice.Entities.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlopsservice.Entities.Request;
import com.mlopsservice.Entities.Repositories.RequestRepository;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    
    public Request getById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public void save(Request request) {
        requestRepository.save(request);
    }
    
}
