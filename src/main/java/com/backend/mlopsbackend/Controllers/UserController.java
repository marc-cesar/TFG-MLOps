package com.backend.mlopsbackend.Controllers;

import com.backend.mlopsbackend.Entities.LoginRequest;
import com.backend.mlopsbackend.Entities.LoginResponse;
import com.backend.mlopsbackend.Entities.LogoutRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class UserController {
    @Autowired(required = true)
    private LogService logService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private final UserService userService;

    public UserController(UserService userResource) { this.userService = userResource; }
    @PostMapping("/user/logIn")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req){
        LoginResponse token = userService.login(req.Username,req.Password);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/user/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest req){
        userService.logout(req.Username,req.Token);

        return ResponseEntity.ok("");
    }

    @PostMapping("/user/signIn")
    public ResponseEntity<LoginResponse> signIn(@RequestBody LoginRequest req){
        System.out.println(req.Username + ' ' +req.Password);
        LoginResponse token = userService.signIn(req.Username,req.Password);

        return ResponseEntity.ok(token);
    }

    /* ADMIN */

    @GetMapping("/admin/getRetrainingConfiguration")
    public ResponseEntity<Object> getRetrainingConfiguration(@RequestParam String token){
        if(userService.isUserAdmin(token)){
            return ResponseEntity.ok(Collections.singletonMap("config", logService.getRetrainingConfiguration() ));
        }
        return ResponseEntity.ok(Collections.singletonMap("error", "Access denied. User must be an admin"));
    }

    @PostMapping("/admin/setRetrainingConfiguration")
    public ResponseEntity<Map<String,String>> setRetrainingConfiguration(@RequestParam String token, @RequestParam Integer minimumRequests, @RequestParam Integer successPercentage){
        if(userService.isUserAdmin(token)){
            String message = logService.setRetrainingConfiguration(minimumRequests, successPercentage) ? "Configuration set properly" : "There was an error.";
            return ResponseEntity.ok(Collections.singletonMap("message", message ));
        }
        return ResponseEntity.ok(Collections.singletonMap("error", "Access denied. User must be an admin"));
    }

    @PostMapping("/admin/forceRetraining")
    public ResponseEntity<Map<String, String>> forceRetraining(@RequestParam String token){
        if (userService.isUserAdmin(token)){
            eventPublisher.publishEvent(new NewRetrainingEvent());
            return ResponseEntity.ok(Collections.singletonMap("message", "Retraining process finished successfully. Check logs for more details"));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access denied. User must be an admin"));
    }

    @GetMapping("/admin/getAllAdmins")
    public ResponseEntity<Object> getAllAdmins(@RequestParam String token){
        if (userService.isUserAdmin(token)){
            return ResponseEntity.ok(Collections.singletonMap("users", userService.getAdminUsers()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access denied. User must be an admin"));
    }

    @PostMapping("/admin/setUserAdmin")
    public ResponseEntity<Object> setUserAdmin(@RequestParam String username, @RequestParam String token){
        return setOrUnsetAdmin(username, token, true);
    }

    @PostMapping("/admin/unsetUserAdmin")
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
