package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Cliente;


@Repository
public interface IClienteRepo extends JpaRepository<Cliente, Long>{
	
}