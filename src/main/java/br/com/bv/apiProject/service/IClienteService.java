package br.com.bv.apiProject.service;

import java.util.List;

import br.com.bv.apiProject.model.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();

	public void save(Cliente cliente, String ipAddress);

	public void updateCliente(Cliente cliente, Integer id);

	Cliente findByCodigo(Integer id);

	void deleteCliente(Integer id);

}
