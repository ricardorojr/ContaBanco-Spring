package com.ricardo.contaBanco.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ricardo.contaBanco.entities.Clientes;
import com.ricardo.contaBanco.repositories.ClientesRepository;
import com.ricardo.contaBanco.services.exceptions.DatabaseException;
import com.ricardo.contaBanco.services.exceptions.ResourceNotFoundException;

@Service
public class ClientesService {

	@Autowired
	private ClientesRepository repository;
	
	//method para retornar lista de clientes
	public List<Clientes> findAll() {
		return repository.findAll();
	}
	
	//method para retornar apenas um cliente pelo Id
	public Clientes findById(Long id) {
		Optional<Clientes> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//method para inserir um cliente
	public Clientes insert(Clientes obj) {
		return repository.save(obj);
	}
	
	//method para deletar um cliente
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	//method para atualizar um cliente
	public Clientes update(Long id, Clientes obj) {
		try {
			Clientes entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}

	//method que faz a atualização de um cliente
	private void updateData(Clientes entity, Clientes obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setCpf(obj.getCpf());
		entity.setDataNascimento(obj.getDataNascimento());
	}
}
