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

import com.back.labhidro.entity.TipoServicio;
import com.back.labhidro.service.TipoServicioS;
import com.back.labhidro.validaciones.RespuestaAccion;

@RestController
@RequestMapping("/api/tipo")
public class TipoServicioControladorRest {
	
	@Autowired
	private TipoServicioS tipoServicio;
	
	@Autowired 
	private RespuestaAccion respAccion;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaTiposServicio(){
		List<TipoServicio> tiposServicio = tipoServicio.listaTipoServicios();
		if(tiposServicio.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de tipos ", tiposServicio);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearTipoServicio(@RequestBody TipoServicio tipoServ){
		TipoServicio tipo= null;
		try {
			tipo = tipoServicio.crearTipoServicio(tipoServ);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Tipo creado", tipo);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarTipoServicio(@PathVariable Long id,@RequestBody TipoServicio tipoServ){

		TipoServicio tipoAct = tipoServicio.buscarTipoServicio(id);
		if(tipoAct == null) {
			return respAccion.listaDatosVacia(false, "no existe ese tipo");
		}

		try {
			tipoAct.setNombre(tipoServ.getNombre());
			tipoAct.setDescripcion(tipoServ.getDescripcion());
			tipoAct.setEstado(tipoServ.getEstado());
			tipoServicio.crearTipoServicio(tipoAct);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Tipo actualizado", tipoAct);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable Long id){
		try {
			tipoServicio.eliminarTipoServicio(id);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Tipo Eliminado", "eliminado");
	}
}
