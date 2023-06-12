package com.reneantunes.reneFood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.reneantunes.reneFood.api.assembler.RestauranteModelAssembler;
import com.reneantunes.reneFood.api.assembler.RestauranteModelDisassembler;
import com.reneantunes.reneFood.api.model.RestauranteModel;
import com.reneantunes.reneFood.api.model.input.RestauranteInput;
import com.reneantunes.reneFood.api.model.view.RestauranteView;
import com.reneantunes.reneFood.domain.exception.EntidadeNaoEncontrataException;
import com.reneantunes.reneFood.domain.exception.NegocioException;
import com.reneantunes.reneFood.domain.model.Restaurante;
import com.reneantunes.reneFood.domain.repository.RestauranteRepository;
import com.reneantunes.reneFood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteModelDisassembler restauranteModelDisassembler;
	
	@JsonView(RestauranteView.Resumo.class)
	@GetMapping
	public List<RestauranteModel> listar(){
		return restauranteModelAssembler.toColletionModel(restauranteRepository.findAll());
		
	}
	
	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome")
	public List<RestauranteModel> listarApenasNomes(){
		return listar();
	}
	
	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable	Long restauranteId) {
	    Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
	    return  restauranteModelAssembler.toModel(restaurante);
	
	}

	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput){
		try {
			Restaurante restaurante = restauranteModelDisassembler.toDomainObject(restauranteInput);
			
			return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restaurante));
		} catch (EntidadeNaoEncontrataException e) {
			throw new NegocioException(e.getMessage());
		}
		
		
				
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId, 
			@RequestBody @Valid RestauranteInput restauranteInput){

		try {
			Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
			
			restauranteModelDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
			
			return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restauranteAtual));
		} catch (EntidadeNaoEncontrataException e) {
			throw new NegocioException(e.getMessage());
		}
	
	
	}
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
		cadastroRestauranteService.ativar(restauranteIds);
		}catch(EntidadeNaoEncontrataException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
		}
	
	@DeleteMapping("/inativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
		cadastroRestauranteService.inativar(restauranteIds);
		
		}catch(EntidadeNaoEncontrataException e ) {
			throw new NegocioException(e.getMessage(), e);
		}
		
			
	}
	
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
		cadastroRestauranteService.ativar(restauranteId);
	}
	
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		cadastroRestauranteService.inativar(restauranteId);
	}
	
	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId){
		cadastroRestauranteService.excluir(restauranteId);
	}
	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long restauranteId) {
		cadastroRestauranteService.abrir(restauranteId);
	}
	
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long restauranteId) {
		cadastroRestauranteService.fechar(restauranteId);
	}


	
	
}
