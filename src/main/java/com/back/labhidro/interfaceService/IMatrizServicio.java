package com.back.labhidro.interfaceService;

import java.util.List;

import com.back.labhidro.entity.Matriz;


public interface IMatrizServicio {
	public List<Matriz> listaMatriz();
	public Matriz crearMatriz(Matriz matriz);
	public Matriz buscarMatriz(Long id);
	public void eliminarMatriz(Long id);
}
