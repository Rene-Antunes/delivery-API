package com.delivery.food.api.openapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.RestauranteModel;
import com.delivery.food.api.model.input.RestauranteInput;
import com.delivery.food.api.model.view.RestauranteView;
import com.delivery.food.api.openapi.model.RestauranteBasicoModelOpenApi;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {
	
	@ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoModelOpenApi.class)
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome" ,
				name = "projeção", paramType = "query", type = "string")
	})
	@JsonView(RestauranteView.Resumo.class)
	List<RestauranteModel> listar();
	
	@ApiOperation(value = "Lista restaurantes", hidden = true)
	List<RestauranteModel> listarApenasNomes();
	
	
	@ApiOperation("Busca um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	RestauranteModel buscar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			@PathVariable Long restauranteId);

	
	@ApiOperation("Cadastra um restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Restaurante cadastrado")
	})
	RestauranteModel adicionar(
			 @ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true)
			@RequestBody @Valid RestauranteInput restauranteInput);
	
	 @ApiOperation("Atualiza um restaurante por ID")
	    @ApiResponses({
	        @ApiResponse(code = 200, message = "Restaurante atualizado"),
	        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	    })
	RestauranteModel atualizar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			@PathVariable Long restauranteId, 
			
			@ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados", 
            required = true)
			@RequestBody @Valid RestauranteInput restauranteInput);
	
	 @ApiOperation("Ativa múltiplos restaurantes")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
	    })
	void ativarMultiplos(
			@ApiParam(name = "corpo", value = "IDs de restaurantes", required = true)
			@RequestBody List<Long> restauranteIds);
	
	 @ApiOperation("Inativa múltiplos restaurantes")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
	    })
	void inativarMultiplos(
			@ApiParam(name = "corpo", value = "IDs de restaurantes", required = true)
			@RequestBody List<Long> restauranteIds);
	
	 @ApiOperation("Ativa um restaurante por ID")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
	        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	    })
	 
	void ativar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			@PathVariable Long restauranteId);
	
	  @ApiOperation("Inativa um restaurante por ID")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
	        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	    })
	  
	void inativar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			@PathVariable Long restauranteId);
	
	  @ApiOperation("Abre um restaurante por ID")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
	        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	    })
	void abrir(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			@PathVariable Long restauranteId);
	
	  @ApiOperation("Fecha um restaurante por ID")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
	        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	    })
	void fechar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			@PathVariable Long restauranteId);
	  
	void remover(@PathVariable Long restauranteId);
	
	
}
