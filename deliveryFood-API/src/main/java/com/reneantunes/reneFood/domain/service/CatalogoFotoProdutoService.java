package com.reneantunes.reneFood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reneantunes.reneFood.domain.model.FotoProduto;
import com.reneantunes.reneFood.domain.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public FotoProduto salvar(FotoProduto fotoProduto) {
		return produtoRepository.save(fotoProduto);
	}
}
