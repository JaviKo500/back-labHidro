package com.back.labhidro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.labhidro.entity.Cliente;
import com.back.labhidro.interfaceService.IClienteServicio;
import com.back.labhidro.repository.IClienteRepo;

@Service
public class ClienteServicio implements IClienteServicio{
	
	@Autowired
	private IClienteRepo clienteRepo;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listaClientes() {
		return clienteRepo.findAll();
	}

	@Override
	@Transactional
	public Cliente crearCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente buscarCliente(Long id) {
		return clienteRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void eliminarCliente(Long id) {
		clienteRepo.deleteById(id);
	}

}
