/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpSpringCassandra.services;

import com.example.tpSpringCassandra.models.CompteBancaire;
import com.example.tpSpringCassandra.repositories.CompteBancaireRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.cassandra.core.mapping.MapIdFactory.id;
import org.springframework.stereotype.Service;

/**
 *
 * @author DG
 */
@Service
public class CompteBancaireServiceImpl implements CompteBancaireService {

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    @Override
    public CompteBancaire saveCompteBancaire(CompteBancaire c,String idTitulaire) {
        CompteBancaire compteBancaire = new CompteBancaire(c.getNumCompte(),idTitulaire, c.getNumAgance(), c.getSolde(), c.isIsEpargne());
        return compteBancaireRepository.save(compteBancaire);
    }

    @Override
    public Optional<CompteBancaire> findById(String numCompte) {
        return compteBancaireRepository.findById(numCompte);
    }

    @Override
    public List<CompteBancaire> findAll() {
        return compteBancaireRepository.findAll();
    }

    @Override
    public void deleteCompteBancaire(String id) {
        Optional<CompteBancaire> data = findById(id);
        if (data.isPresent()) {
            compteBancaireRepository.deleteById(id);
        }
    }

    @Override
    public void depot(String idCompte, double montant, double taux) {
        Optional<CompteBancaire> compteBancaire = compteBancaireRepository.findById(idCompte);
        CompteBancaire compteB = compteBancaire.get();
        compteB.depot(montant, taux);
        compteBancaireRepository.save(compteB);
    }

    @Override
    public void retrait(String idCompte, double montant) {
        Optional<CompteBancaire> compteBancaire = compteBancaireRepository.findById(idCompte);
        CompteBancaire compteB = compteBancaire.get();
        compteB.retrait(montant);
        compteBancaireRepository.save(compteB);
    }

    @Override
    public void virement(String idCompte1, double montant, String idCompte2, double taux, double prixTransaction) {
        Optional<CompteBancaire> compteBancaire1 = compteBancaireRepository.findById(idCompte1);
        CompteBancaire compteB1 = compteBancaire1.get();
        Optional<CompteBancaire> compteBancaire2 = compteBancaireRepository.findById(idCompte2);
        CompteBancaire compteB2 = compteBancaire2.get();
        compteB1.virement(montant, compteB2, taux, prixTransaction);
        compteBancaireRepository.save(compteB1);
        compteBancaireRepository.save(compteB2);
    }

    @Override
    public String afficher(String idCompte) {
        Optional<CompteBancaire> compteBancaire1 = compteBancaireRepository.findById(idCompte);
        CompteBancaire compteB = compteBancaire1.get();
        return compteB.toString();
    }

}
