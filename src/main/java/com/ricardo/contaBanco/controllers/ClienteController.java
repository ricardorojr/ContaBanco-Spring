package com.ricardo.contaBanco.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricardo.contaBanco.entities.Clientes;
import com.ricardo.contaBanco.services.ClientesService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired 
	private ClientesService service;
	
	//retorna todos clientes
	@GetMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Clientes>> findAll() {
		List<Clientes> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//retornar apenas um cliente pelo Id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Clientes> findById(@PathVariable Long id) {
		Clientes obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Insere um cliente
	@PostMapping(produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
			consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Clientes> insert(@RequestBody Clientes obj) {	
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	//Deleta um cliente
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//Atualiza os atributos do cliente pelo Id
	@PutMapping(value = "/{id}")
	public ResponseEntity<Clientes> update(@PathVariable Long id, @RequestBody Clientes obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
