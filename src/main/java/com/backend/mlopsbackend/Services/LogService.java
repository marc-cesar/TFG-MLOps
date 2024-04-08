package com.backend.mlopsbackend.Services;

import com.backend.mlopsbackend.Entities.ServiceLog;
import com.backend.mlopsbackend.Repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class LogService {

    @Autowired(required = true)
    private LogRepository logRepository;

    public void Log(String logText){
        ServiceLog log = new ServiceLog();
        log.log = logText;
        logRepository.save(log);
    }

    public List<ServiceLog> getAllLogs(){
        return logRepository.findAllOrderByDateDesc();
    }
}
