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

import com.delivery.food.api.assembler.PermissaoModelAssembler;
import com.delivery.food.api.model.PermissaoModel;
import com.delivery.food.api.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.delivery.food.domain.model.Grupo;
import com.delivery.food.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PermissaoModel> listar (@PathVariable Long grupoId){
			Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
			
			return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
	}
	
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.associarPermissao(grupoId, permissaoId);
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);
	}
	
	
	
}
