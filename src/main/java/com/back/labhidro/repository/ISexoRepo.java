package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Sexo;

/*
 * @Autor: Javiko
 * Repositorio JPA DAO de Sexo
 * configuracion para las consultas a la base de datos
 * 
 * */

@Repository
public interface ISexoRepo extends JpaRepository<Sexo, Long>{
	
}
