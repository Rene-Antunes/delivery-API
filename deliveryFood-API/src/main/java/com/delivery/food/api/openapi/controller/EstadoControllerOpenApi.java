package com.delivery.food.api.openapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.EstadoModel;
import com.delivery.food.api.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {
	
	
	@ApiOperation("Lista os estados")
	List<EstadoModel> listar();
	
	@ApiOperation("Busca um Estado")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	EstadoModel buscar(
			@ApiParam(value = "ID de um estado", example = "1", required = true)
			@PathVariable Long estadoId);
	
	@ApiOperation("Cadastra um estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado cadastrado", response = Problem.class),
	})
	EstadoModel adicionar(
			@ApiParam(value = "corpo", example = "Representação de um novo estado", required = true)
			@RequestBody @Valid EstadoInput estadoInput);
	
	@ApiOperation("Atualiza um estado já cadastrado por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	EstadoModel atualizar(
			@ApiParam(value = "ID de um estado", example = "1", required = true)
			@PathVariable Long estadoId, 
			
			@ApiParam(value = "corpo", example = "Representação de um estado com novos dados", required = true)
			@RequestBody @Valid EstadoInput estadoInput);
	
	@ApiOperation("Exlcui um estado já cadastrado por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Estado exlcuído"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de um estado", example = "1", required = true)
			@PathVariable Long estadoId);
}
