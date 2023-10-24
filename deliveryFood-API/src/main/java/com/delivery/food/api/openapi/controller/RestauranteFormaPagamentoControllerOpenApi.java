package com.delivery.food.api.openapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
	
@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	
	@ApiOperation("Lista formas de pagamento associadas ao restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	List<FormaPagamentoModel> listar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			@PathVariable Long restauranteId);
	
	@ApiOperation("Desassocião de restaurante com forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
	})
	void desassociar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			@PathVariable Long restauranteId,
			
			@ApiParam(value = "ID de forma de pagamento", example = "1", required = true)
			@PathVariable Long formaPagamentoId);
	
	@ApiOperation("Associação de restaurante com forma de pagamento po ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
	})
	void asassociar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			@PathVariable Long restauranteId, 
			
			@ApiParam(value = "ID de forma de pagamento", example = "1", required = true)
			@PathVariable Long formaPagamentoId);
}
