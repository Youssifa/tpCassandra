/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpSpringCassandra.services;

import com.example.tpSpringCassandra.models.Client;
import com.example.tpSpringCassandra.repositories.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DG
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client saveClient(Client c) {
       Client cl= new Client(c.getId(),c.getNom(),c.getPrenom());
       cl.toString();
        return clientRepository.save(cl) ;
    }

    @Override
    public Optional<Client> findById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClient(String id) {
        Optional<Client> data = findById(id);
        if (data.isPresent()) {
            clientRepository.deleteById(id);
        }
    }

}
