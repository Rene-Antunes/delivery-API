package com.delivery.food.api.openapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.delivery.food.api.exceptionhandler.Problem;
import com.delivery.food.api.model.UsuarioModel;
import com.delivery.food.api.model.input.SenhaInput;
import com.delivery.food.api.model.input.UsuarioComSenhaInput;
import com.delivery.food.api.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {
		
		
		@ApiOperation("Lista os usuários")
		public List<UsuarioModel> listar();
	    
		@ApiOperation("Busca um usuário por ID")
		@ApiResponses({
			@ApiResponse(code= 400, message = "ID do usuário inválido", response = Problem.class),
			@ApiResponse(code= 404, message = "Usuário não encontrado", response = Problem.class)
		})
	    public UsuarioModel buscar(
	    		@ApiParam(value = "ID do usuário", example = "1", required = true)
	    		@PathVariable Long usuarioId);
	    
		@ApiOperation("Cadastra um novo usuário")
		@ApiResponses({
			@ApiResponse(code= 201, message = "Usuário cadastrado"),
		})
	    public UsuarioModel adicionar(
	    		@ApiParam(value = "corpo", example = "Representação de um novo usuário", required = true)
	    		@RequestBody @Valid UsuarioComSenhaInput usuarioInput);
	    
		
		@ApiOperation("Atualiza um usuário com novos dados")
		@ApiResponses({
			@ApiResponse(code= 200, message = "Usuário atualizado"),
			@ApiResponse(code= 404, message = "Usuário não encontrado", response = Problem.class)
		})
	    public UsuarioModel atualizar(
	    		@ApiParam(value = "ID do usuário", example = "1", required = true)
	    		@PathVariable Long usuarioId,
	    		
	    		@ApiParam(value = "corpo", example = "Representação de um novo usuário", required = true)
	    		@RequestBody @Valid UsuarioInput usuarioInput);
	    
		@ApiOperation("Atualiza a senha de um usuário")
		@ApiResponses({
			@ApiResponse(code= 204, message = "Senha alterada com sucesso"),
			@ApiResponse(code= 404, message = "Usuário não encontrado", response = Problem.class)
		})
	    public void alterarSenha(
	    		@ApiParam(value = "ID do usuário", example = "1", required = true)
	    		@PathVariable Long usuarioId, 
	    		
	    		@ApiParam(value = "corpo", example = "Representação de uma nova senha", required = true)
	    		@RequestBody @Valid SenhaInput senha);
	    
}
