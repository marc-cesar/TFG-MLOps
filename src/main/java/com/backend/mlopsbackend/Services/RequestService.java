package com.backend.mlopsbackend.Services;

import com.backend.mlopsbackend.Entities.User;
import com.backend.mlopsbackend.Entities.UserToken;
import com.backend.mlopsbackend.Repositories.UserRepository;
import com.backend.mlopsbackend.Repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.backend.mlopsbackend.Entities.Request;
import com.backend.mlopsbackend.Repositories.RequestRepository;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class RequestService {
    @Autowired(required = true)
    private RequestRepository requestRepository;

    @Autowired(required = true)
    private UserTokenRepository userTokenRepository;

    @Autowired(required = true)
    private UserRepository userRepository;
    
    public Request getById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<Request> getRequestsByToken(String token){
        Optional<UserToken> usr = userTokenRepository.findByToken(token);

        if (usr.isPresent()){
            Long userid = usr.get().userId;
            User user = userRepository.getReferenceById(userid);
            return requestRepository.findAll(); // Add a username
        }

        return null;

    }

    public void save(Request request) {
        requestRepository.save(request);
    }
    
}
