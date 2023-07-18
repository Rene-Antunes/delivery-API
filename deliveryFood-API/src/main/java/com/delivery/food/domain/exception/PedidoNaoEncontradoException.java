package com.reneantunes.reneFood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontrataException {

	private static final long serialVersionUID = 1L;
	
	
	public PedidoNaoEncontradoException(String codigoPedido) {
		super(String.format("Não existe um pedido com código %s", codigoPedido));
	}
}
