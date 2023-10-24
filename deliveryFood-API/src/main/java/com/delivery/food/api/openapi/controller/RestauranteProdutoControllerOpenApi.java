package com.delivery.food.api.openapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.ProdutoModel;
import com.delivery.food.api.model.input.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {
	
	@ApiOperation("Lista produtos associados ao restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
		
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	
	})
	List<ProdutoModel> listar(
			 	@ApiParam(value = "ID do restaurante", example = "1", required = true)
				@PathVariable Long restauranteId,
				
				@ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem", 
                example = "false", defaultValue = "false")
	    		@RequestParam(required = false) boolean incluirInativos);
	
	@
	ApiOperation("Busca produtos associados ao restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
		
		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
	
	})
	ProdutoModel buscar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			@PathVariable Long restauranteId, 
			
			@ApiParam(value = "ID do produto", example = "1", required = true)
			@PathVariable Long produtoId);
	
	@ApiOperation("Cadastra um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Produto cadastrado"),
		
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	
	})
	ProdutoModel adicionar(
			
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			@PathVariable Long restauranteId,
			
			@ApiParam(value = "corpo", example = "Representação de um novo produto", required = true)
	       @RequestBody @Valid ProdutoInput produtoInput);
	
	
	@ApiOperation("Atualiza produtos associados ao restaurante já existente")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Produto atualizado"),
		
		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
	
	})
	ProdutoModel atualizar(
				@ApiParam(value = "ID do restaurante", example = "1", required = true)
				@PathVariable Long restauranteId,
				
				@ApiParam(value = "ID do produto", example = "1", required = true)
				@PathVariable Long produtoId,
				
				@ApiParam(value = "corpo", example = "Representação de um produto com novos dados", required = true)
	            @RequestBody @Valid ProdutoInput produtoInput);  

}
