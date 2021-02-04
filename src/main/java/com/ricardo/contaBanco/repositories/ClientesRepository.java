package com.ricardo.contaBanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricardo.contaBanco.entities.Clientes;


public interface ClientesRepository extends JpaRepository<Clientes, Long> {
	
}  

