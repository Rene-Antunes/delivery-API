package com.delivery.food.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.food.api.assembler.EstadoInputDisassembler;
import com.delivery.food.api.assembler.EstadoModelAssembler;
import com.delivery.food.api.model.EstadoModel;
import com.delivery.food.api.model.input.EstadoInput;
import com.delivery.food.api.openapi.controller.EstadoControllerOpenApi;
import com.delivery.food.domain.exception.EntidadeEmUsoException;
import com.delivery.food.domain.exception.EntidadeNaoEncontrataException;
import com.delivery.food.domain.model.Estado;
import com.delivery.food.domain.repository.EstadoRepository;
import com.delivery.food.domain.service.CadastroEstadoService;



@RestController
@RequestMapping("/estados")
public class EstadoController implements EstadoControllerOpenApi{
	
	@Autowired
	private EstadoRepository estadodoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler; 
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EstadoModel> listar(){
		
		List<Estado> todosEstados = estadodoRepository.findAll();
		
		return  estadoModelAssembler.toCollectionModel(todosEstados);
		
	}
	
	@GetMapping(value = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstadoModel buscar(@PathVariable Long estadoId) {
		Estado esdado = cadastroEstadoService.buscarOufalhar(estadoId);
		
		return estadoModelAssembler.toModel(esdado) ;
	}
	
	@PostMapping(MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		
		estado = cadastroEstadoService.salvar(estado);
		
		return estadoModelAssembler.toModel(estado);
	}
	
	@PutMapping(value = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput){
			Estado estadoAtual = cadastroEstadoService.buscarOufalhar(estadoId);
			estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
			Estado estado = cadastroEstadoService.salvar(estadoAtual);
			
			return estadoModelAssembler.toModel(estado); 
	}
	
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId){
		cadastroEstadoService.excluir(estadoId);
	}
	
	
}
