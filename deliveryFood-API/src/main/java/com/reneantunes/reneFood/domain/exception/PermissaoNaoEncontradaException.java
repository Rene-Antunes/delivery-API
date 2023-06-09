package com.reneantunes.reneFood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontrataException {

	private static final long serialVersionUID = 1L;
	
	public PermissaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public PermissaoNaoEncontradaException(Long permissaoId) {
		this(String.format("Não existe um cadastro de permissão com código %d", permissaoId));
		
	}
}
