package com.delivery.food.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.ServletWebRequest;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.FormaPagamentoModel;
import com.delivery.food.api.model.input.FormaPagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Forma de pagamento")
public interface FormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as forma de pagamento")
	public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);
	
	
	@ApiOperation("Busca uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message ="ID de forma de pagamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message ="Forma de pagamento não encontrada", response = Problem.class)
	})
	
	ResponseEntity<FormaPagamentoModel> buscar(@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
										@PathVariable Long formaPagamentoId, ServletWebRequest request);
	
	@ApiOperation("Cadastra uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 201, message ="forma de pagamento cadastrada"),
	})
	
	FormaPagamentoModel adicionar(
			@ApiParam(name ="corpo", value = "Representação de uma nova forma de pagamento", required = true)
			FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Atualiza uma forma de pagamento por ID ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "forma de pagamento atualizado"),
		@ApiResponse(code = 404, message = "forma de pagamento não encontrado", response = Problem.class)
	})
	FormaPagamentoModel atualizar(
			@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) 
			Long formaPagamentoId, 
			
			@ApiParam(name ="corpo", value = "Representação de uma forma de pagamento com novos dados")	FormaPagamentoInput formaPagamentoInput);
	
	
	
	@ApiOperation("Exclui uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message ="forma de pagamento excluída"),
		@ApiResponse(code = 404, message ="forma de pagamento não encontrada", response = Problem.class)
	})
	
	void remover(@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long FormaPagamentoInput);
	

}
