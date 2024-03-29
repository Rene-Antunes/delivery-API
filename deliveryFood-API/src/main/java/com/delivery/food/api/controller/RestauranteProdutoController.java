package com.delivery.food.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.food.api.assembler.ProdutoInputDisassembler;
import com.delivery.food.api.assembler.ProdutoModelAssembler;
import com.delivery.food.api.model.ProdutoModel;
import com.delivery.food.api.model.input.ProdutoInput;
import com.delivery.food.api.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.delivery.food.domain.model.Produto;
import com.delivery.food.domain.model.Restaurante;
import com.delivery.food.domain.repository.ProdutoRepository;
import com.delivery.food.domain.service.CadastroProdutoService;
import com.delivery.food.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi{

	
		@Autowired
		private ProdutoRepository produtoRepository;
		
		@Autowired
		private CadastroProdutoService cadastroProdutoService;
		
		@Autowired
		private CadastroRestauranteService cadastroRestauranteService;
		
		 @Autowired
		 private ProdutoModelAssembler produtoModelAssembler;
		    
		 @Autowired
		 private ProdutoInputDisassembler produtoInputDisassembler;
		 
		 
		 	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		    public List<ProdutoModel> listar(@PathVariable Long restauranteId,
		    		@RequestParam(required = false) boolean incluirInativos) {
		        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		       
		        List<Produto> todosProdutos = null;
		        
		        if(incluirInativos) {
		        	
		        	todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
		        }else {
		        	todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
		        }
		        
		        
		        return produtoModelAssembler.toCollectionModel(todosProdutos);
		    }
		 
		 @GetMapping(value = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
		 public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
			 Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		        
		        return produtoModelAssembler.toModel(produto);
		    }
		 
		 @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		 @ResponseStatus(HttpStatus.CREATED)
		 public ProdutoModel adicionar(@PathVariable Long restauranteId,
	           @RequestBody @Valid ProdutoInput produtoInput) {
			 	Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		        
		        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		        produto.setRestaurante(restaurante);
		        
		        produto = cadastroProdutoService.salvar(produto);
		        
		        return produtoModelAssembler.toModel(produto);
		    }
		 
		 @PutMapping(value = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
		 public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
		            @RequestBody @Valid ProdutoInput produtoInput) {
		        Produto produtoAtual = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		        
		        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
		        
		        produtoAtual = cadastroProdutoService.salvar(produtoAtual);
		        
		        return produtoModelAssembler.toModel(produtoAtual);
		    }   
		

}
