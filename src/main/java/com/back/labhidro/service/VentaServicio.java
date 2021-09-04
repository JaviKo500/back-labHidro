package com.back.labhidro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.Venta;
import com.back.labhidro.interfaceService.IVentaServicio;
import com.back.labhidro.repository.IVentaRepo;

/*
 * @Autor: Javiko
 * 
 * Servicios para la comuncicacion de repositorio y controlador de Venta
 * Leer, escribir y pasar datos
 * 
 * */

@Service
public class VentaServicio implements IVentaServicio {

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

	// calculamos los costos
	public Venta calcularTotal(Venta venta) {
		Double total = venta.getTotal();
		Double subTotal = total / 1.12;
		Double iva = total - subTotal;
		venta.setTotal(total);
		venta.setIva(iva);
		venta.setSubtotal(subTotal);
		return venta;
	}

}
