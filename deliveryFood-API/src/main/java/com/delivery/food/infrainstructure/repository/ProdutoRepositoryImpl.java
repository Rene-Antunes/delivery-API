package com.delivery.food.infrainstructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.delivery.food.domain.model.FotoProduto;
import com.delivery.food.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public FotoProduto save(FotoProduto fotoProduto) {
		
		return  manager.merge(fotoProduto);
	}
	
	@Transactional
	@Override
	public void delete(FotoProduto foto) {
		manager.remove(foto);
	}
	
	
}
