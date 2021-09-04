package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Sexo;

@Repository
public interface ISexoRepo extends JpaRepository<Sexo, Long>{
	
}
