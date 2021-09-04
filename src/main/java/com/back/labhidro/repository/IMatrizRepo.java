package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Matriz;

/*@Autor: Javiko
 * Repositorio JPA DAO de Matrz
 * configuracion para las consultas a la base de datos
 * 
 * */

@Repository
public interface IMatrizRepo extends JpaRepository<Matriz, Long>{

}
