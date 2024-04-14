package com.backend.mlopsbackend.Services;

import com.backend.mlopsbackend.Entities.RetrainingConfiguration;
import com.backend.mlopsbackend.Entities.ServiceLog;
import com.backend.mlopsbackend.Repositories.LogRepository;
import com.backend.mlopsbackend.Repositories.RetrainingConfigurationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class LogService {

    @Autowired(required = true)
    private LogRepository logRepository;

    @Autowired(required = true)
    private RetrainingConfigurationRepository retrainingConfigurationRepository;

    public void Log(String logText){
        ServiceLog log = new ServiceLog();
        log.log = logText;
        logRepository.save(log);
    }

    public List<ServiceLog> getAllLogs(){
        return logRepository.findAllOrderByDateDesc();
    }

    public RetrainingConfiguration getRetrainingConfiguration() { return retrainingConfigurationRepository.findTopBy();}

    @Transactional
    public boolean setRetrainingConfiguration(Integer minimumRequests, Integer successPercentage){
        RetrainingConfiguration config = this.retrainingConfigurationRepository.findTopBy();
        if(config != null){
            config.setSuccessPercentage(successPercentage);
            config.setMinimumRequests(minimumRequests);
            this.retrainingConfigurationRepository.save(config);
            return true;
        }
        return false;
    }
}
