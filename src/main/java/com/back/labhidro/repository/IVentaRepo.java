package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Venta;

/*
 * @Autor: Javiko
 * Repositorio JPA DAO de Venta
 * configuracion para las consultas a la base de datos
 * 
 * */

@Repository
public interface IVentaRepo extends JpaRepository<Venta, Long>{

}
