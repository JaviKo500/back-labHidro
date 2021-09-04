package com.back.labhidro.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Modelo de la tabla ItemPaqueteVenta,  ORM
 * Configuracion de los datos de la tabla y relaciones
 * 
 * */

@Entity
@Table(name = "items_paquete_venta")
public class ItemPaqueteVenta implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	private PaqueteServicio paqueteServicio;
	
	// calculamos total del costo del item
	public Double getImporte() {
		return cantidad.doubleValue() * paqueteServicio.getPrecio();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public PaqueteServicio getPaqueteServicio() {
		return paqueteServicio;
	}

	public void setPaqueteServicio(PaqueteServicio paqueteServicio) {
		this.paqueteServicio = paqueteServicio;
	}

	private static final long serialVersionUID = 1L;
	
}
