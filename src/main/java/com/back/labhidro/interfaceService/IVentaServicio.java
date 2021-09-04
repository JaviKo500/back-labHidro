package com.back.labhidro.interfaceService;

import com.back.labhidro.entity.Venta;

public interface IVentaServicio {
	
	public Venta buscarVentaId(Long id);
	public Venta crearVenta( Venta venta);
	public void eliminarVentaId(Long id);
	
}
