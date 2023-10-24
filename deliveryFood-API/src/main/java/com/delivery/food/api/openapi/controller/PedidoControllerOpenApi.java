package com.delivery.food.api.openapi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.PedidoModel;
import com.delivery.food.api.model.PedidoResumoModel;
import com.delivery.food.api.model.input.PedidoInput;
import com.delivery.food.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {
	
	 @ApiOperation("Listar pedidos")
	 @ApiImplicitParams({
	    	@ApiImplicitParam(value = "Nome das propriedades para filtrar na resposta, separados por vírgula",
	    			name = "campos", paramType = "query", type = "string")
	    })
	    @GetMapping
	    Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,
	    	@PageableDefault(size=10) Pageable pageable);
	    
	    
	 @ApiOperation("Buscar um pedido")
	 	@ApiImplicitParams({
	    	@ApiImplicitParam(value = "Nome das propriedades para filtrar na resposta, separados por vírgula",
	    			name = "campos", paramType = "query", type = "string")
	    })
	    @GetMapping("/{codigoPedido}")
	   PedidoModel buscar(
	    		@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true)
	    		@PathVariable String codigoPedido);
	    
	 	
	 	 @ApiOperation("Registra um pedido")
	 	 @ApiResponses({
	         @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
	     })
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput);
	    
	    
}
