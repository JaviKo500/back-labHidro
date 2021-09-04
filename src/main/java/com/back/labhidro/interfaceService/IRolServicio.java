package com.back.labhidro.interfaceService;

import java.util.List;

import com.back.labhidro.entity.Rol;

public interface IRolServicio {
	public List<Rol> listaRoles();
	public Rol crearRol(Rol rol);
	public Rol buscarRol(Long id);
	public List<Rol> buscarRolEstado(Boolean estado);
	public void eliminarRol(Long id);
}
