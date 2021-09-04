package com.back.labhidro.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * @Autor: Javiko
 * 
 * Modelo de la tabla PaqueteServicio,  ORM
 * Configuracion de los datos de la tabla y relaciones
 * 
 * */

@Entity
@Table(name = "paquetes_servicios")
public class PaqueteServicio implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	private Double precio;
	private Boolean disponible;

	// Mapeo relaciones para la BD
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ServicioItem> itemsPaquete;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public List<ServicioItem> getItemsPaquete() {
		return itemsPaquete;
	}

	public void setItemsPaquete(List<ServicioItem> itemsPaquete) {
		this.itemsPaquete = itemsPaquete;
	}

	private static final long serialVersionUID = 1L;

}
