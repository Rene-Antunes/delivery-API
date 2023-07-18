package com.delivery.food.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontrataException {

	private static final long serialVersionUID = 1L;
	
	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
		 this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d", 
	                produtoId, restauranteId));
	}

}
