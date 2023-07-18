package com.delivery.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.food.domain.model.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
	
	
}

