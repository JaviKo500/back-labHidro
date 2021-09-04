package com.back.labhidro.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.labhidro.entity.Matriz;
import com.back.labhidro.service.MatrizServicio;
import com.back.labhidro.validaciones.RespuestaAccion;

@RestController
@RequestMapping("/api/matriz")
public class MatrizControladorRest {
	@Autowired
	private MatrizServicio matrizServicio;
	@Autowired 
	private RespuestaAccion respAccion;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaMatriz(){
		List<Matriz> matriz = matrizServicio.listaMatriz();
		if(matriz.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de Matriz", matriz);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearCliente(@RequestBody Matriz matriz){
		
		Matriz nuevoMatriz = null;
		try {
			nuevoMatriz = matrizServicio.crearMatriz(matriz);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Matriz creado", nuevoMatriz);
	}

	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarMatriz(@PathVariable Long id){
		try {
			matrizServicio.eliminarMatriz(id);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Matriz Eliminado", "eliminado");
	}
}
