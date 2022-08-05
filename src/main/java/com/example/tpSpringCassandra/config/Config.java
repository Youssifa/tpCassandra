/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tpSpringCassandra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 *
 * @author DG
 */
@Configuration
@EnableCassandraRepositories
public  class Config extends AbstractCassandraConfiguration{

    @Override
    protected String getKeyspaceName() {
        return "comptabiliteGest";
    }
    
}
