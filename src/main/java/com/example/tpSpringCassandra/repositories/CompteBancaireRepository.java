/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.tpSpringCassandra.repositories;

import com.example.tpSpringCassandra.models.CompteBancaire;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 *
 * @author DG
 */
public interface CompteBancaireRepository extends CassandraRepository<CompteBancaire, String>{
    
}
