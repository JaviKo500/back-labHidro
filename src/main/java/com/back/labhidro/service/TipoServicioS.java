package com.back.labhidro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.TipoServicio;
import com.back.labhidro.interfaceService.ITipoServicio;
import com.back.labhidro.repository.ITipoServicioRepo;

@Service
public class TipoServicioS implements ITipoServicio{
	
	@Autowired
	private ITipoServicioRepo tipoServicioRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoServicio> listaTipoServicios() {
		return tipoServicioRepo.findAll();
	}

	@Override
	@Transactional
	public TipoServicio crearTipoServicio(TipoServicio TipoServicio) {
		return tipoServicioRepo.save(TipoServicio);
	}

	@Override
	@Transactional(readOnly = true)
	public TipoServicio buscarTipoServicio(Long id) {
		return tipoServicioRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void eliminarTipoServicio(Long id) {
		tipoServicioRepo.deleteById(id);
	}

}
