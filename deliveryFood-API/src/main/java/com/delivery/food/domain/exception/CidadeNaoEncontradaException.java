package com.delivery.food.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontrataException {

	
	private static final long serialVersionUID = 1L;
	
	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
		
	}
	
	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format("Não existe um cadastro de cidade com código %d", cidadeId));
	}
}
