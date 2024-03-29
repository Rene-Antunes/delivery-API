package com.delivery.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.food.domain.exception.EntidadeNaoEncontrataException;
import com.delivery.food.domain.model.Cidade;
import com.delivery.food.domain.model.Estado;
import com.delivery.food.domain.repository.CidadeRepository;
import com.delivery.food.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Não existe cadastro de cidade com código %d";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@Transactional
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstadoService.buscarOufalhar(estadoId);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
		
	}
	
	@Transactional
	public void excluir(Long cidadeId) {
		
		try {
		cidadeRepository.deleteById(cidadeId);
		}catch(EmptyResultDataAccessException e) {
			
			throw new EntidadeNaoEncontrataException(String.format(MSG_ENTIDADE_NAO_ENCONTRADA, cidadeId));
		}
	}
	
	public Cidade buscarOuFalhar(Long cidadeId) {
		
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontrataException(
						String.format(MSG_ENTIDADE_NAO_ENCONTRADA, cidadeId)));
		
	}
	
}
