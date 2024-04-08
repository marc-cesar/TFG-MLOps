package com.backend.mlopsbackend.Controllers;

import com.backend.mlopsbackend.Entities.*;
import com.backend.mlopsbackend.Services.LogService;
import com.backend.mlopsbackend.Services.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class LogResource {
    private final LogService logService;

    public LogResource(LogService logService) { this.logService = logService; }

    @GetMapping(value="/all")
    public List<ServiceLog> getAllLogs(){
        return logService.getAllLogs();
    }

}
