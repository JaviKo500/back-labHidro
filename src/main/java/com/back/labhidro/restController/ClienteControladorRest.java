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

import com.back.labhidro.entity.Cliente;
import com.back.labhidro.entity.Direccion;
import com.back.labhidro.entity.Sexo;
import com.back.labhidro.service.ClienteServicio;
import com.back.labhidro.service.SexoServicio;
import com.back.labhidro.validaciones.RespuestaAccion;

@RestController
@RequestMapping("/api/cliente")
public class ClienteControladorRest {
	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private SexoServicio sexoServicio;
	@Autowired 
	private RespuestaAccion respAccion;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaClientes(){
		List<Cliente> clientes = clienteServicio.listaClientes();
		if(clientes.size() == 0) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Lista de clientes", clientes);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente){
		Sexo sexo = sexoServicio.buscarSexo(cliente.getSexo().getId());
		if(sexo == null) {
			return respAccion.listaDatosVacia(false, "no existe ese sexo");
		}
		Cliente nuevoCliente = null;
		try {
			cliente.setSexo(sexo);
			nuevoCliente = clienteServicio.crearCliente(cliente);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Cliente creado", nuevoCliente);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarCliente(@PathVariable String id,@RequestBody Cliente cliente){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		Sexo sexo = sexoServicio.buscarSexo(cliente.getSexo().getId());
		if(sexo == null) {
			return respAccion.listaDatosVacia(false, "no existe ese sexo");
		}
		Cliente clienteAct = clienteServicio.buscarCliente(_id);
		if(clienteAct == null) {
			return respAccion.listaDatosVacia(false, "no existe ese cliente");
		}
		Direccion direccion = cliente.getDireccion();
		try {
			clienteAct.setNombre(cliente.getNombre());
			clienteAct.setApellido(cliente.getApellido());
			clienteAct.setCedula(cliente.getCedula());
			clienteAct.setEmail(cliente.getEmail());
			clienteAct.setEstado(cliente.getEstado());
			clienteAct.setTelefono(cliente.getTelefono());
			clienteAct.setEmail(cliente.getEmail());
			clienteAct.setSexo(sexo);
			direccion.setId(clienteAct.getDireccion().getId());
			clienteAct.setDireccion(direccion);
			clienteServicio.crearCliente(clienteAct);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Cliente actualizado", clienteAct);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable String id){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		try {
			clienteServicio.eliminarCliente(_id);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Cliente Eliminado", "eliminado");
	}
}
