package com.back.labhidro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.Usuario;
import com.back.labhidro.interfaceService.IUsuarioService;
import com.back.labhidro.repository.IUsuarioRepo;

/*
 * @Autor: Javiko
 * 
 * Servicios para la comuncicacion de repositorio y controlador de Usuario
 * Leer, escribir y pasar datos
 * 
 * */

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
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
	
	
	// < ---------------- para la autentificacion --------------------------- >

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByNombreAndEstado(nombre, true);
		if(usuario == null) {
			throw new UsernameNotFoundException("Error en el login: " + nombre + " no encontrado.");
		}
		List<GrantedAuthority> authorities = usuario.getRoles()
													.stream()
													.map(role -> new SimpleGrantedAuthority(role.getNombre()))
													.collect(Collectors.toList());  
		return new User(usuario.getNombre(), usuario.getPassword(), usuario.getEstado(), true, true, true, authorities);
	}
	
	
	

}
