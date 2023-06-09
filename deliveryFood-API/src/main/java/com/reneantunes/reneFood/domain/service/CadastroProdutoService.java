package com.reneantunes.reneFood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reneantunes.reneFood.domain.exception.ProdutoNaoEncontradoException;
import com.reneantunes.reneFood.domain.model.Produto;
import com.reneantunes.reneFood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(restauranteId, produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId)); 
		
	}
}
