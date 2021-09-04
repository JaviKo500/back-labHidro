package com.back.labhidro.restController;


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

import com.back.labhidro.entity.Cliente;
import com.back.labhidro.entity.Matriz;
import com.back.labhidro.entity.Venta;
import com.back.labhidro.service.ClienteServicio;
import com.back.labhidro.service.MatrizServicio;
import com.back.labhidro.service.VentaServicio;
import com.back.labhidro.validaciones.RespuestaAccion;

@RestController
@RequestMapping("/api/venta")
public class VentaControladorRest {
	@Autowired
	private VentaServicio ventaServicio;
	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private MatrizServicio matrizServicio;

	@Autowired
	private RespuestaAccion respAccion;
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> listaVenta(@PathVariable String id){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		Venta venta = ventaServicio.buscarVentaId(_id);
		if(venta == null) {
			return respAccion.listaDatosVacia(false, "No hay datos");
		}
		return respAccion.accionCumplida(true, "Venta", venta);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearVenta(@RequestBody Venta venta){
		Cliente cliente = clienteServicio.buscarCliente(venta.getCliente().getId());
		if(cliente == null) {
			return respAccion.listaDatosVacia(false, "No hay ese cliente");
		}
		Matriz  matriz= matrizServicio.buscarMatriz(venta.getMatriz().getId());
		if(matriz == null) {
			return respAccion.listaDatosVacia(false, "No hay esa sucursal");
		}
		Venta nuevoVenta = null;
		try {
			venta.setCliente(cliente);
			venta.setMatriz(matriz);
			if(venta.getPaquetes().size() > 0 || venta.getServicios().size() > 0) {
				venta = calcularTotal(venta);
			}
			nuevoVenta = ventaServicio.crearVenta(venta);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, ex.getMessage());
		}
		return respAccion.accionCumplida(true, "Venta realizada", nuevoVenta);
	}
	
	public Venta calcularTotal( Venta venta) {
		Double total = venta.getTotal();
		Double subTotal = total/1.12;
		Double iva =  total-subTotal;
		venta.setTotal(total);
		venta.setIva(iva);
		venta.setSubtotal(subTotal);
		return venta;
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarVenta(@PathVariable String id){
		Long _id = null;
		try {
			_id = Long.parseLong(id);
		} catch (IllegalArgumentException e) {
			return respAccion.listaDatosVacia(false, "Parametro de busqueda inválido");
		}
		try {
			ventaServicio.eliminarVentaId(_id);
		} catch (DataAccessException ex) {
			return respAccion.errorBD(false, "Error en la bd");
		}
		return respAccion.accionCumplida(true, "Venta Eliminada", "eliminado");
	}
}
