package com.back.labhidro.interfaceService;

import java.util.List;

import com.back.labhidro.entity.Servicio;


public interface IServicioServi {
	public List<Servicio> listaServicios();
	public Servicio crearServicio(Servicio servicio);
	public Servicio buscarServicio(Long id);
	public List<Servicio> buscarServicioDisponible(Boolean disponible);
	public void eliminarServicio(Long id);
}
