package com.back.labhidro.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.labhidro.entity.Usuario;
import com.back.labhidro.service.UsuarioService;
import com.back.labhidro.validaciones.RespuestaAccion;

/*
 * @Autor: Javiko
 * 
 * Controlador para el crud de Usuario
 * Configuracion de endpoints para el api HTTP
 * 
 * */

@RestController
@RequestMapping("/api/usuario")
public class UsuarioControladorRest {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired 
	private RespuestaAccion respAccion;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaUsuario(){
		List<Usuario> usuarios = usuarioService.listaUsuarios();
		if(usuarios.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de usuarios", usuarios);
	}
	@GetMapping("/lista/estado")
	public ResponseEntity<?> listaUsuarioEstado(){
		List<Usuario> usuarios = usuarioService.buscarUsuarioEstado(true);
		if(usuarios.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de Usuarios Activos", usuarios);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario = null;
		try {
			// encriptamos la contraseña
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			nuevoUsuario = usuarioService.crearUsuario(usuario);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, ex.getMessage());
		}
		return respAccion.accionCumplida(true, "Usuario creado", nuevoUsuario);
	}

	@PutMapping("actualizar/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		Usuario usuActual = usuarioService.buscarUsuario(_id);
		if(usuActual == null) {
			return respAccion.listaDatosVacia(false, "No hay ese usuario en la bd");
		}
		try {
			usuActual.setEmail(usuario.getEmail());
			usuActual.setNombre(usuario.getNombre());
			// encriptamos la contraseña
			usuActual.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuActual.setRoles(usuario.getRoles());
			usuActual.setEstado(usuario.getEstado());
			usuarioService.crearUsuario(usuActual);
		} catch (DataAccessException e) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Usuario actualizado", usuActual);

	}
	@PutMapping("actualizar/estado/{id}")
	public ResponseEntity<?> deshabilitarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		
		Usuario usuActual = usuarioService.buscarUsuario(_id);
		if(usuActual == null) {
			return respAccion.listaDatosVacia(false, "No hay ese usuario en la bd");
		}
		try {
			usuActual.setEstado(usuario.getEstado());
			usuarioService.crearUsuario(usuActual);
		} catch (DataAccessException e) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		if(usuario.getEstado()) {			
			return respAccion.accionCumplida(true, "Usuario actualizado", "Usuario habilitado");
		}
		return respAccion.accionCumplida(true, "Usuario actualizado", "Usuario desactivado");

	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable String id){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		
		try {
			usuarioService.eliminarUsuario(_id);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Usuario Eliminado", "eliminado");
	}
}
