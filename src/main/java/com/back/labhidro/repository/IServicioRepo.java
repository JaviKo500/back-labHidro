package com.back.labhidro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Servicio;

/*
 * @Autor: Javiko
 * Repositorio JPA DAO de Servicio
 * configuracion para las consultas a la base de datos
 * 
 * */

@Repository
public interface IServicioRepo extends JpaRepository<Servicio, Long>{
	public List<Servicio> findByDisponible(Boolean disponible);
}
