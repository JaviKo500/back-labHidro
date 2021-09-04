package com.back.labhidro.interfaceService;

import java.util.List;

import com.back.labhidro.entity.Usuario;


public interface IUsuarioService {
	public List<Usuario> listaUsuarios();
	public Usuario crearUsuario(Usuario usuario);
	public Usuario buscarUsuario(Long id);
	public List<Usuario> buscarUsuarioEstado(Boolean estado);
	public void eliminarUsuario(Long id);
}
