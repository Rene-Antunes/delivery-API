package com.delivery.food.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.delivery.food.domain.model.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
	
	@Query("select max(dataAtualizacao) from FormaPagamento")
	OffsetDateTime getDataUltimaAtualizacao();
	
	@Query("select dataAtualizacao from FormaPagamento where id = :formaPagamentoId")
	OffsetDateTime getDataAtualizacaoById(Long formaPagamentoId);
	
}

