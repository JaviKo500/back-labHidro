package com.back.labhidro.restController;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.back.labhidro.entity.PaqueteServicio;
import com.back.labhidro.imagenes.RutaImagenes;
import com.back.labhidro.imagenes.UploadFileImagen;
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
	
	@Autowired
	private UploadFileImagen uploadService;
	
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
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verImg(@PathVariable String nombreFoto){
		Resource recurso = null;
		try {
			recurso = uploadService.cargar(nombreFoto, RutaImagenes.RUTA_PAQUETES);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera,	 HttpStatus.OK);
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
	
	@PostMapping("/img-upload")
	public ResponseEntity<?> imgUpload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		PaqueteServicio paquete = paqueteServicio.buscarPaqueteServicio(id);
		if(!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo, RutaImagenes.RUTA_PAQUETES);
			} catch (Exception e) {
				return respAccion.errorBD(false, "No se pudo cargar la img");
			}
			String nombreFotoAnterior = paquete.getImg();
			uploadService.eliminar(nombreFotoAnterior, RutaImagenes.RUTA_PAQUETES);
			paquete.setImg(nombreArchivo);
			paqueteServicio.crearPaqueteServicio(paquete);
		}
		return respAccion.accionCumplida(true, "Imagen subida", "Acción realizada");
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
			PaqueteServicio paquete= paqueteServicio.buscarPaqueteServicio(_id);
			String nombreFotoAnterior = paquete.getImg();
			uploadService.eliminar(nombreFotoAnterior, RutaImagenes.RUTA_PAQUETES);
			paqueteServicio.eliminarPaqueteServicio(_id);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Pack Eliminado", "eliminado");
	}
}
