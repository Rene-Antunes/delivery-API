package com.reneantunes.reneFood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reneantunes.reneFood.domain.exception.EntidadeEmUsoException;
import com.reneantunes.reneFood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.reneantunes.reneFood.domain.model.FormaPagamento;
import com.reneantunes.reneFood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {
	
	private static final String FORMA_DE_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso";
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	@Transactional
	public void excluir(Long formaPagamentoId) {
		try {
			formaPagamentoRepository.deleteById(formaPagamentoId);
			formaPagamentoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(formaPagamentoId);
		
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(FORMA_DE_PAGAMENTO_EM_USO, formaPagamentoId));
	
		}
	}
	
	public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId)
				.orElseThrow(()-> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
	}
	
}
