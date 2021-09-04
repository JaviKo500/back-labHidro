package com.back.labhidro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.PaqueteServicio;

@Repository
public interface IPaqueteServicioRepo extends JpaRepository<PaqueteServicio, Long>{
	public List<PaqueteServicio> findByDisponible(Boolean disponible);
}
