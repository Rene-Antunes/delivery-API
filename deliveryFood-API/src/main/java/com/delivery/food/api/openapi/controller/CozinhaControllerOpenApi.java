package com.delivery.food.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.CozinhaModel;
import com.delivery.food.api.model.input.CozinhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {
	
	@ApiOperation("Lista as cozinhas")
	public Page<CozinhaModel> listar (Pageable pageable);
	
	@ApiOperation("Busca uma cozinha")
	@ApiResponses({
		@ApiResponse(code = 400, message = " ID do cozinha inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrado", response = Problem.class)
	})
	public CozinhaModel buscar(
			@ApiParam(value = "ID de um cozinha", example = "1", required = true)
			Long cozinhaId);
	
	@ApiOperation("Cadastra um nova cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrada")
	})
	public CozinhaModel adicionar(
			@ApiParam(name = "Corpo", value = "Representação de uma nova cozinha", required = true)
			CozinhaInput cozinhaInput);
	
	@ApiOperation("Atualiza uma cozinha por ID ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualizado"),
		@ApiResponse(code = 404, message = "Cozinha não encontrado", response = Problem.class)
	})
	public CozinhaModel atualizar(
			@ApiParam(value = "ID de um cozinha", example = "1", required = true)
			Long cozinhaId, 
			
			@ApiParam(name = "Corpo", value = "Representação de uma cozinha com os novos dados", required = true)
			CozinhaInput cozinhaInput);
	
	@ApiOperation("Exclui uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cozinha excluido"),
		@ApiResponse(code = 404, message = "Cozinha não encontrado", response = Problem.class)
	})
	public void remover(
			@ApiParam(value = "ID de um cozinha", example = "1", required = true)
			Long cozinhaId);

}
