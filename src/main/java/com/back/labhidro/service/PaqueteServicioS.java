package com.back.labhidro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.PaqueteServicio;
import com.back.labhidro.interfaceService.IPaqueteServicio;
import com.back.labhidro.repository.IPaqueteServicioRepo;

/*
 * @Autor: Javiko
 * 
 * Servicios para la comuncicacion de repositorio y controlador de PaqueteServicio
 * Leer, escribir y pasar datos
 * 
 * */

@Service
public class PaqueteServicioS implements IPaqueteServicio{
	
	@Autowired
	private IPaqueteServicioRepo paqueteServicioRepo;

	@Override
	@Transactional(readOnly = true)
	public List<PaqueteServicio> listaPaquetesServicio() {
		return paqueteServicioRepo.findAll();
	}

	@Override
	@Transactional
	public PaqueteServicio crearPaqueteServicio(PaqueteServicio paqueteServicio) {
		return paqueteServicioRepo.save(paqueteServicio);
	}

	@Override
	@Transactional(readOnly = true)
	public PaqueteServicio buscarPaqueteServicio(Long id) {
		return paqueteServicioRepo.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PaqueteServicio> buscarPaqueteServicioDisponibles(Boolean disponible) {
		return paqueteServicioRepo.findByDisponible(disponible);
	}

	@Override
	@Transactional
	public void eliminarPaqueteServicio(Long id) {
		paqueteServicioRepo.deleteById(id);		
	}
	
}
