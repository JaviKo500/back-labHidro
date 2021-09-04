package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Cliente;

/*
 * Repositorio JPA DAO de Cliente
 * configuracion para las consultas a la base de datos
 * 
 * */

@Repository
public interface IClienteRepo extends JpaRepository<Cliente, Long>{
	
}
