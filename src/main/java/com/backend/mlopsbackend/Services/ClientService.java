package com.backend.mlopsbackend.Services;

import com.backend.mlopsbackend.Entities.Client;
import com.backend.mlopsbackend.Entities.Request;
import com.backend.mlopsbackend.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = "com.mlopsservice.Repositories")
public class ClientService {

    @Autowired(required = true)
    private ClientRepository clientRepository;

    public Optional<Client> getClientFromDNI(String dni){
        return clientRepository.findByDni(dni);
    }

    public void SaveClient(Client client){
        clientRepository.save(client);
    }
}
