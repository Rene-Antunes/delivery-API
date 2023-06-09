package com.reneantunes.reneFood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reneantunes.reneFood.api.assembler.UsuarioInputDisassembler;
import com.reneantunes.reneFood.api.assembler.UsuarioModelAssembler;
import com.reneantunes.reneFood.api.model.UsuarioModel;
import com.reneantunes.reneFood.api.model.input.SenhaInput;
import com.reneantunes.reneFood.api.model.input.UsuarioComSenhaInput;
import com.reneantunes.reneFood.api.model.input.UsuarioInput;
import com.reneantunes.reneFood.domain.model.Usuario;
import com.reneantunes.reneFood.domain.repository.UsuarioRepository;
import com.reneantunes.reneFood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
		@Autowired
	    private UsuarioRepository usuarioRepository;
	    
	    @Autowired
	    private CadastroUsuarioService cadastroUsuario;
	    
	    @Autowired
	    private UsuarioModelAssembler usuarioModelAssembler;
	    
	    @Autowired
	    private UsuarioInputDisassembler usuarioInputDisassembler;
	    
	    
	    @GetMapping
	    public List<UsuarioModel> listar(){
	    	List<Usuario> todosUsarios = usuarioRepository.findAll();
	    	
	    	return usuarioModelAssembler.toCollectionModel(todosUsarios);
	    	
	    }
	    
	    
	    @GetMapping("/{usuarioId}")
	    public UsuarioModel buscar(@PathVariable Long usuarioId){
	    	Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
	    	
	    	return usuarioModelAssembler.toModel(usuario);
	    	
	    }
	    
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
	    	Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
	    	usuario = cadastroUsuario.salvar(usuario);
	    	return usuarioModelAssembler.toModel(usuario);
	    	
	    }
	    
	    @PutMapping("/{usuarioId}")
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
