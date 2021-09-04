package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Direccion;

@Repository
public interface IDireccionRepo extends JpaRepository<Direccion, Long>{

}
