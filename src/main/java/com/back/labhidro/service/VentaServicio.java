package com.back.labhidro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.Venta;
import com.back.labhidro.interfaceService.IVentaServicio;
import com.back.labhidro.repository.IVentaRepo;

@Service
public class VentaServicio implements IVentaServicio{
	
	@Autowired
	private IVentaRepo ventaRepo;
	
	@Override
	@Transactional(readOnly = true)
	public Venta buscarVentaId(Long id) {
		return ventaRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Venta crearVenta(Venta venta) {
		return ventaRepo.save(venta);
	}

	@Override
	@Transactional
	public void eliminarVentaId(Long id) {
		ventaRepo.deleteById(id);
	}

}
