package com.delivery.food.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.food.api.assembler.UsuarioInputDisassembler;
import com.delivery.food.api.assembler.UsuarioModelAssembler;
import com.delivery.food.api.model.UsuarioModel;
import com.delivery.food.api.model.input.SenhaInput;
import com.delivery.food.api.model.input.UsuarioComSenhaInput;
import com.delivery.food.api.model.input.UsuarioInput;
import com.delivery.food.api.openapi.controller.UsuarioControllerOpenApi;
import com.delivery.food.domain.model.Usuario;
import com.delivery.food.domain.repository.UsuarioRepository;
import com.delivery.food.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {
	
		@Autowired
	    private UsuarioRepository usuarioRepository;
	    
	    @Autowired
	    private CadastroUsuarioService cadastroUsuario;
	    
	    @Autowired
	    private UsuarioModelAssembler usuarioModelAssembler;
	    
	    @Autowired
	    private UsuarioInputDisassembler usuarioInputDisassembler;
	    
	    
	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<UsuarioModel> listar(){
	    	List<Usuario> todosUsarios = usuarioRepository.findAll();
	    	
	    	return usuarioModelAssembler.toCollectionModel(todosUsarios);
	    	
	    }
	    
	    
	    @GetMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public UsuarioModel buscar(@PathVariable Long usuarioId){
	    	Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
	    	
	    	return usuarioModelAssembler.toModel(usuario);
	    	
	    }
	    
	    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
	    	Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
	    	usuario = cadastroUsuario.salvar(usuario);
	    	return usuarioModelAssembler.toModel(usuario);
	    	
	    }
	    
	    @PutMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public UsuarioModel atualizar(@PathVariable Long usuarioId,
	    		@RequestBody @Valid UsuarioInput usuarioInput) {
	    	Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
	    	usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
	    	usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
	    	
	    	return usuarioModelAssembler.toModel(usuarioAtual);
	    }
	    
	    
	    @PutMapping("/{usuarioId}/senha")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
	    	cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
	    }
	    
	    
}
