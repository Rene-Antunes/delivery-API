package com.reneantunes.reneFood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reneantunes.reneFood.domain.exception.PermissaoNaoEncontradaException;
import com.reneantunes.reneFood.domain.model.Permissao;
import com.reneantunes.reneFood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {
	
	@Autowired
	private PermissaoRepository permissaoRepository; 
	
	public Permissao buscarOuFalhar(Long permissaoId) {
			
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}

}
