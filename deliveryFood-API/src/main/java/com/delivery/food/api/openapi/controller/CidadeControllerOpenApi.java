package com.delivery.food.api.openapi.controller;

import java.util.List;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.CidadeModel;
import com.delivery.food.api.model.input.CidadeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {
	
	@ApiOperation("Lista as cidades")
	 List<CidadeModel> listar();
	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message ="ID da cidade inválido", response = Problem.class),
		@ApiResponse(code = 404, message ="Cidade não encontrada", response = Problem.class)
	})
	
	 CidadeModel buscar(@ApiParam(value = "ID de uma cidade", example = "1", required = true)
								Long cidadeId);
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message ="Cidade cadastrada"),
	})
	
	 CidadeModel adicionar(
			@ApiParam(name ="corpo", value = "Representação de uma nova cidade", required = true)
			CidadeInput cidadeInput);
	
	@ApiOperation("Atualiza uma cidade por ID ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizado"),
		@ApiResponse(code = 404, message = "Cidade não encontrado", response = Problem.class)
	})
	 CidadeModel atualizar(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true) 
			Long cidadeId, 
			
			@ApiParam(name ="corpo", value = "Representação de uma cidade com novos dados", required = true) CidadeInput cidadeInput);
	
	
	
	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Cidade excluída"),
		@ApiResponse(code = 404, message ="Cidade não encontrada", response = Problem.class)
	})
	
	 void remover(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);
	

}
