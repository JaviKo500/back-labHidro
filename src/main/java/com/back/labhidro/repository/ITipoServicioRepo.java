package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.TipoServicio;

/*
 * @Autor: Javiko
 * Repositorio JPA DAO de Servicio
 * configuracion para las consultas a la base de datos
 * 
 * */

@Repository
public interface ITipoServicioRepo extends JpaRepository<TipoServicio, Long>{

}
