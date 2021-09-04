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
 * Modelo de la tabla ItemServicioVenta,  ORM
 * Configuracion de los datos de la tabla y relaciones
 * 
 * */

@Entity
@Table(name = "items_servicio_venta")
public class ItemServicioVenta implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	private Double cantidadMl;
	private Double cantidadLugar;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Servicio servicio;
	
	// calculamos total del costo del item
	public Double getImporte() {
		return (cantidadMl * 0.01)+ (cantidadLugar* 100) * cantidad.doubleValue();
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

	public Double getCantidadMl() {
		return cantidadMl;
	}

	public void setCantidadMl(Double cantidadMl) {
		this.cantidadMl = cantidadMl;
	}

	public Double getCantidadLugar() {
		return cantidadLugar;
	}

	public void setCantidadLugar(Double cantidadLugar) {
		this.cantidadLugar = cantidadLugar;
	}



	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	private static final long serialVersionUID = 1L;

}
