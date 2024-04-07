package com.backend.mlopsbackend.Controllers;

import com.backend.mlopsbackend.Entities.LoginRequest;
import com.backend.mlopsbackend.Entities.LoginResponse;
import com.backend.mlopsbackend.Entities.LogoutRequest;
import com.backend.mlopsbackend.Services.UserService;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userResource) { this.userService = userResource; }
    @PostMapping("/logIn")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req){
        LoginResponse token = userService.login(req.Username,req.Password);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest req){
        userService.logout(req.Username,req.Token);

        return ResponseEntity.ok("");
    }

    @PostMapping("/signIn")
    public ResponseEntity<LoginResponse> signIn(@RequestBody LoginRequest req){
        System.out.println(req.Username + ' ' +req.Password);
        LoginResponse token = userService.signIn(req.Username,req.Password);

        return ResponseEntity.ok(token);
    }


}
