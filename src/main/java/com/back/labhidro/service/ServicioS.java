package com.back.labhidro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.Servicio;
import com.back.labhidro.interfaceService.IServicioServi;
import com.back.labhidro.repository.IServicioRepo;

@Service
public class ServicioS implements IServicioServi{
	
	@Autowired 
	private IServicioRepo servicioRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Servicio> listaServicios() {
		return servicioRepo.findAll();
	}

	@Override
	@Transactional
	public Servicio crearServicio(Servicio servicio) {
		return servicioRepo.save(servicio);
	}

	@Override
	@Transactional(readOnly = true)
	public Servicio buscarServicio(Long id) {
		return servicioRepo.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = true)
	public List<Servicio> buscarServicioDisponible(Boolean disponible) {
		return servicioRepo.findByDisponible(disponible);
	}
	@Override
	@Transactional
	public void eliminarServicio(Long id) {
		servicioRepo.deleteById(id);
	}

	

}
