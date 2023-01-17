package com.springboot.test.targhe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.test.targhe.entity.Veicolo;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo, Integer> {

}
