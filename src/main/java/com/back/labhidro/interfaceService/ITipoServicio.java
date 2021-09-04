package com.back.labhidro.interfaceService;

import java.util.List;

import com.back.labhidro.entity.TipoServicio;


public interface ITipoServicio {
	public List<TipoServicio> listaTipoServicios();
	public TipoServicio crearTipoServicio(TipoServicio TipoServicio);
	public TipoServicio buscarTipoServicio(Long id);
	public void eliminarTipoServicio(Long id);
}
