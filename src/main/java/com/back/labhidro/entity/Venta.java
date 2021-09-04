package com.back.labhidro.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * @Autor: Javiko
 * 
 * Modelo de la tabla Ventas,  ORM
 * Configuracion de los datos de la tabla y relaciones
 * 
 * */

@Entity
@Table(name = "ventas")
public class Venta implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double subtotal;
	private Double iva;
	private Double total;
	
	@Temporal(TemporalType.DATE)
	private Date fechaCompra;
	
	// Mapeo relaciones para la BD
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne
	private Matriz matriz;;
	
	@JsonIgnoreProperties(value = {"ventas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "venta_id")
	private List<ItemServicioVenta> servicios;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "venta_id")
	private List<ItemPaqueteVenta> paquetes;
	
	public Venta() {
		this.servicios = new ArrayList<>();
		this.paquetes = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemServicioVenta> getServicios() {
		return servicios;
	}

	public void setServicios(List<ItemServicioVenta> servicios) {
		this.servicios = servicios;
	}

	public List<ItemPaqueteVenta> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<ItemPaqueteVenta> paquetes) {
		this.paquetes = paquetes;
	}
	
	// calculamos total del costo del de la venta
	public Double getTotal() {
		total = 0.00;
		for (ItemServicioVenta item: servicios) {
			total += item.getImporte();
		}
		for (ItemPaqueteVenta item: paquetes) {
			total += item.getImporte();
		}
		return total;
	}
	private static final long serialVersionUID = 1L;
	
}
