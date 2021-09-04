package com.back.labhidro.interfaceService;

import java.util.List;

import com.back.labhidro.entity.PaqueteServicio;

public interface IPaqueteServicio {
	public List<PaqueteServicio> listaPaquetesServicio();
	public PaqueteServicio crearPaqueteServicio(PaqueteServicio paqueteServicio);
	public PaqueteServicio buscarPaqueteServicio(Long id);
	public List<PaqueteServicio> buscarPaqueteServicioDisponibles(Boolean disponible);
	public void eliminarPaqueteServicio(Long id);
}
