package com.delivery.food.domain.repository;

import com.delivery.food.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
	
	FotoProduto save(FotoProduto fotoProduto);
	void delete(FotoProduto foto);
}
