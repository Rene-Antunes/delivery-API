package com.reneantunes.reneFood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reneantunes.reneFood.api.assembler.GrupoModelAssembler;
import com.reneantunes.reneFood.api.model.GrupoModel;
import com.reneantunes.reneFood.domain.model.Usuario;
import com.reneantunes.reneFood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
	
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	 @Autowired
	    private GrupoModelAssembler grupoModelAssembler;
	 
	 
	 @GetMapping
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
