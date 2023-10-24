package com.delivery.food.api.openapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {
	
	@ApiOperation("Lista as permissões associadas a um grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	List<PermissaoModel> listar (
			@ApiParam(value = "ID do grupo", example = "1", required = true)
			@PathVariable Long grupoId);
	
	@ApiOperation("Associação de permissao com grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = Problem.class)
	})
	void associar(
			@ApiParam(value = "ID do grupo", example = "1", required = true)
			@PathVariable Long grupoId,
			
			@ApiParam(value = "ID do permissão", example = "1", required = true)
			@PathVariable Long permissaoId);

	@ApiOperation("Desassociação de permissao com grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = Problem.class)
	})
	void desassociar(
			@ApiParam(value = "ID do grupo", example = "1", required = true)
			@PathVariable Long grupoId, 
			
			@ApiParam(value = "ID do permissão", example = "1", required = true)
			@PathVariable Long permissaoId);

}
