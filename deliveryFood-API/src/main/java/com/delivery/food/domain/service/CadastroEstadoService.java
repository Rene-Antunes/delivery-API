package com.delivery.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.food.domain.exception.EntidadeEmUsoException;
import com.delivery.food.domain.exception.EntidadeNaoEncontrataException;
import com.delivery.food.domain.exception.EstadoNaoEncontrataException;
import com.delivery.food.domain.model.Estado;
import com.delivery.food.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ENTIDA_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";

	@Autowired
	private EstadoRepository estadoRepository;
	
	
	@Transactional
	public Estado salvar(Estado estado) {
		
		return estadoRepository.save(estado);
	}
	
	@Transactional
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontrataException(estadoId);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ENTIDA_EM_USO, estadoId));
		}
	}
	
	public Estado buscarOufalhar(Long estadoId) {
		
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EstadoNaoEncontrataException(estadoId));
		
	}
	
	
}
