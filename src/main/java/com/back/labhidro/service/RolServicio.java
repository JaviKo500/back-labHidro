package com.back.labhidro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.Rol;
import com.back.labhidro.interfaceService.IRolServicio;
import com.back.labhidro.repository.IRolRepo;

/*
 * @Autor: Javiko
 * 
 * Servicios para la comuncicacion de repositorio y controlador de Rol
 * Leer, escribir y pasar datos
 * 
 * */

@Service
public class RolServicio implements IRolServicio{
	
	@Autowired
	private IRolRepo rolRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Rol> listaRoles() {
		return rolRepo.findAll();
	}

	@Override
	@Transactional
	public Rol crearRol(Rol rol) {
		return rolRepo.save(rol);
	}

	@Override
	@Transactional(readOnly = true)
	public Rol buscarRol(Long id) {
		return rolRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rol> buscarRolEstado(Boolean estado) {
		return rolRepo.findByEstado(estado);
	}

	@Override
	@Transactional
	public void eliminarRol(Long id) {
		rolRepo.deleteById(id);
	}

}
