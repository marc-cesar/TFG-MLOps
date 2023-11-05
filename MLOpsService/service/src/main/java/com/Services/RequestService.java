package com.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entities.Request;
import com.Repositories.RequestRepository;

import java.util.List;

@Service
public class RequestService {
    @Autowired
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
