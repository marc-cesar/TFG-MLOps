package com.backend.mlopsbackend.Controllers;

import com.backend.mlopsbackend.Entities.*;
import com.backend.mlopsbackend.Events.NewRetrainingEvent;
import com.backend.mlopsbackend.Services.LogService;
import com.backend.mlopsbackend.Services.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class LogResource {
    private final LogService logService;
    private final UserService userService;

    public LogResource(LogService logService, UserService userService) {
        this.logService = logService;
        this.userService = userService;
    }

    @GetMapping(value="/all")
    public ResponseEntity<List<ServiceLog>> getAllLogs(@RequestParam String token){
        if (userService.isUserAdmin(token)){
            return ResponseEntity.ok(logService.getAllLogs());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
