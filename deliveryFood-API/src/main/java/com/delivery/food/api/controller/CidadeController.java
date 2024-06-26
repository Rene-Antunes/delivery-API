package com.delivery.food.api.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.delivery.food.api.ResourceUriHelper;
import com.delivery.food.api.assembler.CidadeInputDisassembler;
import com.delivery.food.api.assembler.CidadeModelAssembler;
import com.delivery.food.api.model.CidadeModel;
import com.delivery.food.api.model.input.CidadeInput;
import com.delivery.food.api.openapi.controller.CidadeControllerOpenApi;
import com.delivery.food.domain.exception.EstadoNaoEncontrataException;
import com.delivery.food.domain.exception.NegocioException;
import com.delivery.food.domain.model.Cidade;
import com.delivery.food.domain.repository.CidadeRepository;
import com.delivery.food.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CidadeModel> listar(){
		List<Cidade> todasCidades = cidadeRepository.findAll();
		
		return cidadeModelAssembler.toCollectionModel(todasCidades);
		
	}
	@GetMapping(value = "/{cidadeId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		 Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		
		 return cidadeModelAssembler.toModel(cidade);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput){
		
		try {
			 Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			
			 cidade = cadastroCidade.salvar(cidade);
			 
			CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
			
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());
			
			return cidadeModel;
			
		} catch (EstadoNaoEncontrataException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
	}
	
	@PutMapping(value = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
		
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			
			cidadeAtual = cadastroCidade.salvar(cidadeAtual);
						
			return cidadeModelAssembler.toModel(cidadeAtual);
			
		} catch (EstadoNaoEncontrataException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
		
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId){
		cadastroCidade.excluir(cidadeId);
	}
	
	
	
	
}
