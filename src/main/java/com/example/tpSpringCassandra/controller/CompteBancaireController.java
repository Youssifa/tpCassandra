/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpSpringCassandra.controller;

import com.example.tpSpringCassandra.models.CompteBancaire;
import com.example.tpSpringCassandra.services.CompteBancaireService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DG
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/compteBancaires")
public class CompteBancaireController {

    @Autowired
    private CompteBancaireService compteBancaireService;

    @RequestMapping(value = "/addCompteBancaire/{idTitulaire}", method = RequestMethod.POST)
    public ResponseEntity<CompteBancaire> createCompteBancaire(@RequestBody CompteBancaire c, @PathVariable("idTitulaire") String idTitulaire) {
        try {
            CompteBancaire compteBancaire = compteBancaireService.saveCompteBancaire(c, idTitulaire);
            return new ResponseEntity<>(compteBancaire, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getCompteBancaire/{}", method = RequestMethod.GET)
    public ResponseEntity<Optional<CompteBancaire>> getCompteBancaire(@PathVariable("numCompte") String numCompte) {
        try {
            Optional<CompteBancaire> compteBancaire = compteBancaireService.findById(numCompte);
            return new ResponseEntity<>(compteBancaire, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getAllCompteBancaire", method = RequestMethod.GET)
    public ResponseEntity<List<CompteBancaire>> getAll() {
        try {
            List<CompteBancaire> compteBancaires = compteBancaireService.findAll();
            if (compteBancaires.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(compteBancaires, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/deleteCompteBancaire/{numCompte}", method = RequestMethod.GET)
    public ResponseEntity<Object> deleteCompteBancaire(@PathVariable("numCompte") String numCompte) {
        try {
            compteBancaireService.deleteCompteBancaire(numCompte);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/depot/{idCompte}/{montant}/{taux}", method = RequestMethod.POST)
    public ResponseEntity<CompteBancaire> depot(@PathVariable("idCompte") String idCompte, @PathVariable("montant") double montant, @PathVariable("taux") double taux) {
        try {
            compteBancaireService.depot(idCompte, montant, taux);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/retrait/{idCompte}/{montant}", method = RequestMethod.POST)
    public ResponseEntity<CompteBancaire> retrait(@PathVariable("idCompte") String idCompte, @PathVariable("montant") double montant) {
        try {
            compteBancaireService.retrait(idCompte, montant);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/retrait/{idCompte1}/{idCompte2}/{montant}/{taux}{prixTransaction}", method = RequestMethod.POST)
    public ResponseEntity<CompteBancaire> virement(@PathVariable("idCompte1") String idCompte1,
            @PathVariable("idCompte2") String idCompte2, 
            @PathVariable("montant") double montant,
            @PathVariable("taux") double taux,
            @PathVariable("prixTransaction") double prixTransaction) {
        try {
            compteBancaireService.virement(idCompte1, montant,idCompte2,taux,prixTransaction);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
