/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpSpringCassandra.controller;

import com.example.tpSpringCassandra.models.Client;
import com.example.tpSpringCassandra.repositories.ClientRepository;
import com.example.tpSpringCassandra.services.ClientService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DG
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
@Autowired
    private ClientRepository clientRepository;
    @PostMapping(value = "/addClient")
    public ResponseEntity<Client> createProd(@RequestBody Client c) {
        try {
            Client cl= new Client(c.getId(),c.getNom(),c.getPrenom());
          Client client = clientRepository.save(cl);
            //Client client = clientService.saveClient(c);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getClient/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Client>> getClient(@PathVariable("id") String id) {
        try {
            Optional<Client> categ = clientService.findById(id);
            return new ResponseEntity<>(categ, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllClient")
    //@RequestMapping(value = "/getAllClient", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAll() {
        try {
            List<Client> clients = clientService.findAll();
            if (clients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/deleteClient/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> deleteClient(@PathVariable("id") String id) {
        try {
            clientService.deleteClient(id);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
