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

import com.back.labhidro.entity.PaqueteServicio;
import com.back.labhidro.service.PaqueteServicioS;
import com.back.labhidro.validaciones.RespuestaAccion;

/*
 * @Autor: Javiko
 * 
 * Controlador para el crud de PaqueteServico
 * Configuracion de endpoints para el api HTTP
 * 
 * */

@RestController
@RequestMapping("/api/paquete")
public class PaqueteServicioControladorRest {
	
	@Autowired
	private PaqueteServicioS paqueteServicio;

	@Autowired 
	private RespuestaAccion respAccion;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaPaquetesServicio(){
		List<PaqueteServicio> paquetes = paqueteServicio.listaPaquetesServicio();
		if(paquetes.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de paquetes", paquetes);
	}
	@GetMapping("/lista/disponible")
	public ResponseEntity<?> listaPaquetesServicioDisponible(){
		List<PaqueteServicio> paquetes = paqueteServicio.buscarPaqueteServicioDisponibles(true);
		if(paquetes.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de paquetes", paquetes);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearPaqueteServicio(@RequestBody PaqueteServicio paquete){
		if(paquete == null) {			
			return respAccion.respuestaValidacion(false, "Campos incompletos", "error");
		}
		PaqueteServicio paqueteNuevo = null;
		try {
			paqueteNuevo = paqueteServicio.crearPaqueteServicio(paquete);
		} catch (DataAccessException ex) {
			
			//return respAccion.errorBD(false, ex.getMessage());
			
			System.out.println(ex);
		}
		return respAccion.accionCumplida(true, "Paquete creado", paqueteNuevo);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarPaqueteServicio(@PathVariable String id,@RequestBody PaqueteServicio paquete){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		if(paquete == null) {			
			return respAccion.respuestaValidacion(false, "Campos incompletos", "error");
		}
		PaqueteServicio paqueteAct = paqueteServicio.buscarPaqueteServicio(_id);
		if(paqueteAct == null) {
			return respAccion.listaDatosVacia(false, "no existe ese pack");
		}
		try {
			paqueteAct.setNombre(paquete.getNombre());
			paqueteAct.setDescripcion(paquete.getDescripcion());
			paqueteAct.setDisponible(paquete.getDisponible());
			paqueteAct.setItemsPaquete(paquete.getItemsPaquete());
			paqueteServicio.crearPaqueteServicio(paqueteAct);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Paquete actualizado", paqueteAct);
	}
	
	@PutMapping("/actualizar/ofertar/{id}")
	public ResponseEntity<?> actualizarServicoOferta(@PathVariable String id, @RequestBody PaqueteServicio paquete){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		if(paquete == null) {			
			return respAccion.respuestaValidacion(false, "Campos incompletos", "error");
		}
		PaqueteServicio paqueteAct = paqueteServicio.buscarPaqueteServicio(_id);
		if(paqueteAct == null) {
			return respAccion.listaDatosVacia(false, "no existe ese pack");
		}
		try {
			paqueteAct.setDisponible(paquete.getDisponible());
			paqueteServicio.crearPaqueteServicio(paqueteAct);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		if(paquete.getDisponible()) {			
			return respAccion.accionCumplida(true, "Paquete actualizado", "Paquete ofertado");
		}
		return respAccion.accionCumplida(true, "Paquete actualizado", "Paquete no ofertado");
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarPaqueteServicio(@PathVariable String id){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		
		try {
			paqueteServicio.eliminarPaqueteServicio(_id);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Pack Eliminado", "eliminado");
	}
}
