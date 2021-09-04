package com.back.labhidro.interfaceService;

import java.util.List;

import com.back.labhidro.entity.Sexo;

public interface ISexoServicio {
	public List<Sexo> listaSexos();
	public Sexo crearSexo(Sexo sexo);
	public Sexo buscarSexo(Long id);
	public void eliminarSexo(Long id);
}
