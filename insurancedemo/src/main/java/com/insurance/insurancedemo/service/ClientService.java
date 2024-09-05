package com.insurance.insurancedemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.insurancedemo.model.Client;
import com.insurance.insurancedemo.repo.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Client> getByClientId(Long clientId) {
        return clientRepository.findById(clientId);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    public Client updateClient(Long clientId, Client clientDetails) {
        return clientRepository.findById(clientId).map(client -> {
            client.setClientName(clientDetails.getClientName());
            client.setClientEmail(clientDetails.getClientEmail());
            client.setClientPhone(clientDetails.getClientPhone());
            client.setClientAddress(clientDetails.getClientAddress());
            return clientRepository.save(client);
        }).orElseGet(() -> {
            clientDetails.setClientId(clientId);
            return clientRepository.save(clientDetails);
        });
    }
}