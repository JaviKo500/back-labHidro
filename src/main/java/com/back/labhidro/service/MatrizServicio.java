package com.back.labhidro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.Matriz;
import com.back.labhidro.interfaceService.IMatrizServicio;
import com.back.labhidro.repository.IMatrizRepo;

@Service
public class MatrizServicio implements IMatrizServicio{
	
	@Autowired
	private IMatrizRepo matrizRepo;
	@Override
	@Transactional(readOnly = true)
	public List<Matriz> listaMatriz() {
		return matrizRepo.findAll();
	}

	@Override
	@Transactional
	public Matriz crearMatriz(Matriz matriz) {
		return matrizRepo.save(matriz);
	}

	@Override
	@Transactional(readOnly = true)
	public Matriz buscarMatriz(Long id) {
		return matrizRepo.getById(id);
	}

	@Override
	@Transactional
	public void eliminarMatriz(Long id) {
		matrizRepo.deleteById(id);
	}

}
