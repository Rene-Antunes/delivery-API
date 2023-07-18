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

import com.reneantunes.reneFood.api.assembler.FormaPagamentoAssembler;
import com.reneantunes.reneFood.api.assembler.RestauranteModelAssembler;
import com.reneantunes.reneFood.api.assembler.RestauranteModelDisassembler;
import com.reneantunes.reneFood.api.model.FormaPagamentoModel;
import com.reneantunes.reneFood.api.model.RestauranteModel;
import com.reneantunes.reneFood.api.model.input.RestauranteInput;
import com.reneantunes.reneFood.domain.exception.EntidadeNaoEncontrataException;
import com.reneantunes.reneFood.domain.exception.NegocioException;
import com.reneantunes.reneFood.domain.model.Restaurante;
import com.reneantunes.reneFood.domain.repository.RestauranteRepository;
import com.reneantunes.reneFood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	FormaPagamentoAssembler formaPagamentoAssembler;
	
	@GetMapping
	public List<FormaPagamentoModel> listar(@PathVariable Long restauranteId){
		
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		return formaPagamentoAssembler.toCollectionModel(restaurante.getFormasPagamento());
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestauranteService.desassociarFormaDePagamento(restauranteId, formaPagamentoId);
	}
	
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void asassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestauranteService.asassociarFormaDePagamento(restauranteId, formaPagamentoId);
	}
	

	
	
}
