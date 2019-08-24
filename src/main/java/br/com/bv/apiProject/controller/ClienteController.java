package br.com.bv.apiProject.controller; 

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bv.apiProject.model.Cliente;
import br.com.bv.apiProject.service.IClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;

	@RequestMapping("/clientes")
	public List<Cliente> getClientes(){
		return clienteService.findAll();
	}
	
	@RequestMapping("/clientes/{id}")
	public Cliente getCliente(@PathVariable Integer id){
		return clienteService.findByCodigo(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/clientes")
	public void addCliente(@RequestBody Cliente cliente, HttpServletRequest request){
		clienteService.save(cliente, request.getRemoteAddr());
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/clientes/{id}")
	public void updateCliente(@RequestBody Cliente cliente, @PathVariable Integer id){
		clienteService.updateCliente(cliente, id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/clientes/{id}")
	public void deleteliente(@PathVariable Integer id){
		clienteService.deleteCliente(id);
	}
	
	
}
