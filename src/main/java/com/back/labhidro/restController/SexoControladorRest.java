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

import com.back.labhidro.entity.Sexo;
import com.back.labhidro.service.SexoServicio;
import com.back.labhidro.validaciones.RespuestaAccion;

/*
 * @Autor: Javiko
 * 
 * Controlador para el crud de Sexo
 * Configuracion de endpoints para el api HTTP
 * 
 * */

@RestController
@RequestMapping("/api/sexo")
public class SexoControladorRest {

	@Autowired
	private SexoServicio sexoServicio;
	
	@Autowired
	private RespuestaAccion respAccion;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaSexos(){
		List<Sexo> sexos = sexoServicio.listaSexos();
		if(sexos.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de sexos", sexos);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearSexo(@RequestBody Sexo sexo){
		Sexo nuevoSexo = null;
		try {
			nuevoSexo = sexoServicio.crearSexo(sexo);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Sexo creado", nuevoSexo);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarSexo(@PathVariable Long id,@RequestBody Sexo sexo){
		Sexo sexoAct = sexoServicio.buscarSexo(id);
		if(sexoAct == null) {
			return respAccion.listaDatosVacia(false, "no existe es sexo");
		}
		try {
			sexoAct.setTipo(sexo.getTipo());
			sexoAct.setEstado(sexo.getEstado());
			sexoServicio.crearSexo(sexoAct);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Sexo actualizado", sexoAct);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarSexo(@PathVariable Long id){
		try {
			sexoServicio.eliminarSexo(id);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Sexo Eliminado", "eliminado");
	}
}
