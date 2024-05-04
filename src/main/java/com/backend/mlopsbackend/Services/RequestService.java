package com.backend.mlopsbackend.Services;

import com.backend.mlopsbackend.Entities.Assessment;
import com.backend.mlopsbackend.Entities.User;
import com.backend.mlopsbackend.Repositories.UserRepository;
import com.backend.mlopsbackend.Repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.backend.mlopsbackend.Repositories.AssessmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class RequestService {
    @Autowired(required = true)
    private AssessmentRepository assessmentRepository;

    @Autowired(required = true)
    private UserTokenRepository userTokenRepository;

    @Autowired(required = true)
    private UserRepository userRepository;

    @Autowired(required = true)
    private UserService userService;
    
    public Assessment getById(Long id) {
        return assessmentRepository.findById(id).orElse(null);
    }

    public List<Assessment> getAllRequests() {
        return assessmentRepository.findAll();
    }

    public Optional<List<Assessment>> getRequestsByToken(String token){
        Optional<User> user = userService.getUserFromToken(token);
        if (user.isPresent()){
            User loggedUser = user.get();
            return loggedUser.isAdmin ? Optional.of(assessmentRepository.findAll()) : assessmentRepository.findAllByRequesterId(loggedUser.id);
        } else
            return Optional.empty();
    }

    public void save(Assessment assessment) {
        assessmentRepository.save(assessment);
    }
    
}
