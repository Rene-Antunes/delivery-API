package com.reneantunes.reneFood.domain.repository;

import com.reneantunes.reneFood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
	
	FotoProduto save(FotoProduto fotoProduto);
	void delete(FotoProduto foto);
}
