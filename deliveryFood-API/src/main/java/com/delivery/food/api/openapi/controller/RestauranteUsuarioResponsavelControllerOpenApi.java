package com.delivery.food.api.openapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {
	
	@ApiOperation("Lista os usuários responsáveis associados ao restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	List<UsuarioModel> listar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			@PathVariable Long restauranteId);
	
	@ApiOperation("Desassocião de usuários reponsáveis associados ao restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		
		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
	})
   void desassociar(
    		@ApiParam(value = "ID do restaurante", example = "1", required = true)
    		@PathVariable Long restauranteId,
    		
    		@ApiParam(value = "ID do usuário", example = "1", required = true)
    		@PathVariable Long usuarioId);
	

	@ApiOperation("Associão de usuários reponsáveis associados ao restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
		
		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
	})
    void associar(
    		@ApiParam(value = "ID do restaurante", example = "1", required = true)
    		@PathVariable Long restauranteId,
    		
    		@ApiParam(value = "ID do usuário", example = "1", required = true)
    		@PathVariable Long usuarioId);
}
