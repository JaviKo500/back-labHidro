package com.back.labhidro.interfaceService;

import java.util.List;

import com.back.labhidro.entity.Cliente;

public interface IClienteServicio {
	public List<Cliente> listaClientes();
	public Cliente crearCliente(Cliente cliente);
	public Cliente buscarCliente(Long id);
	public void eliminarCliente(Long id);
}
