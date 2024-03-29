package com.delivery.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.food.api.assembler.GrupoModelAssembler;
import com.delivery.food.api.model.GrupoModel;
import com.delivery.food.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.delivery.food.domain.model.Usuario;
import com.delivery.food.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi{
	
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	 
	 
	 @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	 public List<GrupoModel> listar(@PathVariable Long usuarioId){
		 Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		 
		 return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
	 }
	 
	 @DeleteMapping("/{grupoId}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	 public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		 cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
	 }
	 
	 @PutMapping("/{grupoId}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	 public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		 cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
	 }

}
