package com.back.labhidro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.Sexo;
import com.back.labhidro.interfaceService.ISexoServicio;
import com.back.labhidro.repository.ISexoRepo;

/*
 * @Autor: Javiko
 * 
 * Servicios para la comuncicacion de repositorio y controlador de Sexo
 * Leer, escribir y pasar datos
 * 
 * */

@Service
public class SexoServicio implements ISexoServicio{
	
	@Autowired
	private ISexoRepo sexoRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Sexo> listaSexos() {
		return sexoRepo.findAll();
	}

	@Override
	@Transactional
	public Sexo crearSexo(Sexo sexo) {
		return sexoRepo.save(sexo);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Sexo buscarSexo(Long id) {
		return sexoRepo.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void eliminarSexo(Long id) {
		sexoRepo.deleteById(id);
	}

}
