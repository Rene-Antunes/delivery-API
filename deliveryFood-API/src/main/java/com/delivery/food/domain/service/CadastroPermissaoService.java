package com.delivery.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.food.domain.exception.PermissaoNaoEncontradaException;
import com.delivery.food.domain.model.Permissao;
import com.delivery.food.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {
	
	@Autowired
	private PermissaoRepository permissaoRepository; 
	
	public Permissao buscarOuFalhar(Long permissaoId) {
			
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}

}
