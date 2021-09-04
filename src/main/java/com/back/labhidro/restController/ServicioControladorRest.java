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

import com.back.labhidro.entity.Servicio;
import com.back.labhidro.entity.TipoServicio;
import com.back.labhidro.service.ServicioS;
import com.back.labhidro.service.TipoServicioS;
import com.back.labhidro.validaciones.RespuestaAccion;

@RestController
@RequestMapping("/api/servicio")
public class ServicioControladorRest {
	@Autowired
	private ServicioS servicioServ;
	@Autowired
	private TipoServicioS tipoServicio;
	@Autowired 
	private RespuestaAccion respAccion;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaServicios(){
		List<Servicio> servicios = servicioServ.listaServicios();
		if(servicios.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de servicios", servicios);
	}
	
	@GetMapping("/lista/disponible")
	public ResponseEntity<?> listaServiciosDisponibles(){
		List<Servicio> servicios = servicioServ.buscarServicioDisponible(true);
		if(servicios.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de servicios", servicios);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearServicio(@RequestBody Servicio servicio){
		TipoServicio tipo = tipoServicio.buscarTipoServicio(servicio.getTipoServicio().getId());
		if(tipo == null) {
			return respAccion.listaDatosVacia(false, "no existe esa categoria de servicio");
		}
		Servicio servicioNuevo = null;
		try {
			servicio.setTipoServicio(tipo);
			servicioNuevo = servicioServ.crearServicio(servicio);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Servicio creado", servicioNuevo);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarServicio(@PathVariable String id,@RequestBody Servicio servicio){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		TipoServicio tipo = tipoServicio.buscarTipoServicio(servicio.getTipoServicio().getId());
		if(tipo == null) {
			return respAccion.listaDatosVacia(false, "no existe esa categoria de servicio");
		}
		Servicio servicioAct = servicioServ.buscarServicio(_id);
		if(servicioAct == null) {
			return respAccion.listaDatosVacia(false, "no existe ese servicio");
		}
		try {
			servicioAct.setNombre(servicio.getNombre());
			servicioAct.setDescripcion(servicio.getDescripcion());
			servicioAct.setDisponible(servicio.getDisponible());
			servicioAct.setTipoServicio(tipo);
			servicioServ.crearServicio(servicioAct);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Servicio actualizado", servicioAct);
	}
	
	@PutMapping("/actualizar/ofertar/{id}")
	public ResponseEntity<?> actualizarServicoOferta(@PathVariable String id, @RequestBody Servicio servicio){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		Servicio servicioAct = servicioServ.buscarServicio(_id);
		if(servicioAct == null) {
			return respAccion.listaDatosVacia(false, "no existe ese servicio");
		}
		try {
			servicioAct.setDisponible(servicio.getDisponible());
			servicioServ.crearServicio(servicioAct);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		if(servicio.getDisponible()) {			
			return respAccion.accionCumplida(true, "Servicio actualizado", "Servicio ofertado");
		}
		return respAccion.accionCumplida(true, "Servicio actualizado", "Servicio no ofertado");
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarServicio(@PathVariable String id){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		try {
			servicioServ.eliminarServicio(_id);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Servicio Eliminado", "eliminado");
	}
}
