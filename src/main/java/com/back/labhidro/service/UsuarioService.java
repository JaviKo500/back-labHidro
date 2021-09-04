package com.back.labhidro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.Usuario;
import com.back.labhidro.interfaceService.IUsuarioService;
import com.back.labhidro.repository.IUsuarioRepo;

@Service
public class UsuarioService implements IUsuarioService{
	@Autowired
	private IUsuarioRepo usuarioRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listaUsuarios() {
		return usuarioRepo.findAll();
	}

	@Override
	@Transactional
	public Usuario crearUsuario(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuario(Long id) {
		return usuarioRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarUsuarioEstado(Boolean estado) {
		return usuarioRepo.findByEstado(estado);
	}

	@Override
	@Transactional
	public void eliminarUsuario(Long id) {
		usuarioRepo.deleteById(id);
	}

}
