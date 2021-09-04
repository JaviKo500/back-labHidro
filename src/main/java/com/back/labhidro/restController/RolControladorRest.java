package com.back.labhidro.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.labhidro.entity.Rol;
import com.back.labhidro.service.RolServicio;
import com.back.labhidro.validaciones.RespuestaAccion;

/*
 * @Autor: Javiko
 * 
 * Controlador para el crud de Rol
 * Configuracion de endpoints para el api HTTP
 * 
 * */

@RestController
@RequestMapping("/api/rol")
public class RolControladorRest {
	
	@Autowired
	private RolServicio rolService;
	
	@Autowired 
	private RespuestaAccion respAccion;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaRol(){
		List<Rol> roles = rolService.listaRoles();
		if(roles.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de roles", roles);
	}
	@GetMapping("/lista/estado")
	public ResponseEntity<?> listaRolEstado(){
		List<Rol> roles = rolService.buscarRolEstado(true);
		if(roles.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de Roles Activos", roles);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearRol(@RequestBody Rol rol){
		Rol nuevoRol = null;
		try {
			nuevoRol = rolService.crearRol(rol);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, ex.getMessage());
		}
		return respAccion.accionCumplida(true, "Rol creado", nuevoRol);
	}

	@PutMapping("actualizar/{id}")
	public ResponseEntity<?> actualizarRol(@PathVariable String id, @RequestBody Rol rol) {
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		Rol rolAct = rolService.buscarRol(_id);
		if(rolAct == null) {
			return respAccion.listaDatosVacia(false, "No hay ese Rol en la bd");
		}
		try {
			rolAct.setDescripcion(rol.getDescripcion());
			rolAct.setEstado(rol.getEstado());
			//usuActual.setPassword(passwordEncoder.encode(usuario.getPassword()));
			rolService.crearRol(rolAct);
		} catch (DataAccessException e) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Rol actualizado", rolAct);

	}
	@PutMapping("actualizar/estado/{id}")
	public ResponseEntity<?> deshabilitarUsuario(@PathVariable String id, @RequestBody Rol rol) {
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		
		Rol rolActual = rolService.buscarRol(_id);
		if(rolActual == null) {
			return respAccion.listaDatosVacia(false, "No hay ese Rol en la bd");
		}
		try {
			rolActual.setEstado(rol.getEstado());
			rolService.crearRol(rolActual);
		} catch (DataAccessException e) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		if(rol.getEstado()) {			
			return respAccion.accionCumplida(true, "Rol actualizado", "Rol habilitado");
		}
		return respAccion.accionCumplida(true, "Rol actualizado", "Rol desactivado");

	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarRol(@PathVariable String id){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		
		try {
			rolService.eliminarRol(_id);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Rol Eliminado", "eliminado");
	}
}
