package com.back.labhidro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Usuario;

/*
 * @Autor: Javiko
 * Repositorio JPA DAO de Usuario
 * configuracion para las consultas a la base de datos
 * 
 * */

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Long>{
	public List<Usuario> findByEstado(Boolean estado);
	public Usuario findByNombreAndEstado(String nombre, Boolean estado);
}
