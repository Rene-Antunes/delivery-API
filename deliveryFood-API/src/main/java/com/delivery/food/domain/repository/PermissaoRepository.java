package com.delivery.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.food.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
	
}

