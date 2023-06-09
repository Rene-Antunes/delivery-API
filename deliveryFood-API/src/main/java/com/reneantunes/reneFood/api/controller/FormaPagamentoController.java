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
import com.reneantunes.reneFood.api.assembler.FormaPagamentoDisassembler;
import com.reneantunes.reneFood.api.model.FormaPagamentoModel;
import com.reneantunes.reneFood.api.model.input.FormaPagamentoInput;
import com.reneantunes.reneFood.domain.model.FormaPagamento;
import com.reneantunes.reneFood.domain.repository.FormaPagamentoRepository;
import com.reneantunes.reneFood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@Autowired
	private FormaPagamentoAssembler formaPagamentoAssembler;
	
	@Autowired
	private FormaPagamentoDisassembler formaPagamentoDisassembler;
	
	@GetMapping
	public List<FormaPagamentoModel> listar() {
		
		List<FormaPagamento> todasFormasPagamento = formaPagamentoRepository.findAll();
		
		return formaPagamentoAssembler.toCollectionModel(todasFormasPagamento);
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		return formaPagamentoAssembler.toModel(formaPagamento);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoDisassembler.toDomainObject(formaPagamentoInput);
		
		formaPagamento = cadastroFormaPagamentoService.salvar(formaPagamento);
		
		return formaPagamentoAssembler.toModel(formaPagamento);
		
	}
	
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
			@RequestBody @Valid FormaPagamentoInput formaPagamentoInput ) {
		
		FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		formaPagamentoDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		
		formaPagamentoAtual = cadastroFormaPagamentoService.salvar(formaPagamentoAtual);
		
		return formaPagamentoAssembler.toModel(formaPagamentoAtual);
		
		
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		cadastroFormaPagamentoService.excluir(formaPagamentoId);
		
	}
	
	
}
