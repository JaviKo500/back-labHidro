package com.back.labhidro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Rol;

/*
 * @Autor: Javiko
 * Repositorio JPA DAO de Rol
 * configuracion para las consultas a la base de datos
 * 
 * */

@Repository
public interface IRolRepo extends JpaRepository<Rol, Long>{
	public List<Rol> findByEstado(Boolean estado);
}
