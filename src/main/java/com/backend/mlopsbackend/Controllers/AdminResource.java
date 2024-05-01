package com.backend.mlopsbackend.Controllers;

import com.backend.mlopsbackend.Entities.User;
import com.backend.mlopsbackend.Events.NewRetrainingEvent;
import com.backend.mlopsbackend.Services.LogService;
import com.backend.mlopsbackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class AdminResource {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired(required = true)
    private UserService userService;

    @Autowired(required = true)
    private LogService logService;

    @GetMapping("/getRetrainingConfiguration")
    public ResponseEntity<Object> getRetrainingConfiguration(@RequestParam String token){
        if(userService.isUserAdmin(token)){
            return ResponseEntity.ok(Collections.singletonMap("config", logService.getRetrainingConfiguration() ));
        }
        return ResponseEntity.ok(Collections.singletonMap("error", "Access denied. User must be an admin"));
    }

    @PostMapping("/setRetrainingConfiguration")
    public ResponseEntity<Map<String,String>> setRetrainingConfiguration(@RequestParam String token, @RequestParam Integer minimumRequests, @RequestParam Integer successPercentage){
        if(userService.isUserAdmin(token)){
            String message = logService.setRetrainingConfiguration(minimumRequests, successPercentage) ? "Configuration set properly" : "There was an error.";
            return ResponseEntity.ok(Collections.singletonMap("message", message ));
        }
        return ResponseEntity.ok(Collections.singletonMap("error", "Access denied. User must be an admin"));
    }

    @PostMapping("/forceRetraining")
    public ResponseEntity<Map<String, String>> forceRetraining(@RequestParam String token){
        if (userService.isUserAdmin(token)){
            eventPublisher.publishEvent(new NewRetrainingEvent());
            return ResponseEntity.ok(Collections.singletonMap("message", "Retraining process finished successfully. Check logs for more details"));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access denied. User must be an admin"));
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<Object> getAllAdmins(@RequestParam String token){
        if (userService.isUserAdmin(token)){
            return ResponseEntity.ok(Collections.singletonMap("users", userService.getAdminUsers()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access denied. User must be an admin"));
    }

    @PostMapping("/setUserAdmin")
    public ResponseEntity<Object> setUserAdmin(@RequestParam String username, @RequestParam String token){
        return setOrUnsetAdmin(username, token, true);
    }

    @PostMapping("/unsetUserAdmin")
    public ResponseEntity<Object> unsetUserAdmin(@RequestParam String username, @RequestParam String token){
        return setOrUnsetAdmin(username, token, false);
    }

    private ResponseEntity<Object> setOrUnsetAdmin(String username, String token, Boolean isSet) {
        if (userService.isUserAdmin(token)){
            Optional<User> user = userService.getUserFromToken(token);
            if(user.isPresent() && user.get().username.equals(username)){
                return ResponseEntity.ok(Collections.singletonMap("error", "You cannot remove your own administrator access."));
            }
            Boolean ok = userService.setAdminUser(username, isSet);
            if (ok)
                return ResponseEntity.ok(Collections.singletonMap("users", isSet ? "This user is now administrator." : "This user is not an administrator anymore."));
            else
                return ResponseEntity.ok(Collections.singletonMap("error", "The user does not exist."));
        }
        return ResponseEntity.ok(Collections.singletonMap("error", "Access denied. User must be an admin."));
    }
}
