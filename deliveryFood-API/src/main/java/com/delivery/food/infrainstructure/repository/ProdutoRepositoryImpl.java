package com.reneantunes.reneFood.infrainstructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.reneantunes.reneFood.domain.model.FotoProduto;
import com.reneantunes.reneFood.domain.repository.ProdutoRepositoryQueries;

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
