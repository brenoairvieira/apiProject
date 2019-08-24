package br.com.bv.apiProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bv.apiProject.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
