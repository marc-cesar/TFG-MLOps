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

    @Autowired(required = true)
    private UserService userService;
    
    public Request getById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Optional<List<Request>> getRequestsByToken(String token){
        Optional<User> user = userService.getUserFromToken(token);
        if (user.isPresent()){
            User loggedUser = user.get();
            return loggedUser.IsAdmin ? Optional.of(requestRepository.findAll()) : requestRepository.findAllByRequesterId(loggedUser.id);
        } else
            return Optional.empty();
    }

    public void save(Request request) {
        requestRepository.save(request);
    }
    
}
