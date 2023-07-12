package com.reneantunes.reneFood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reneantunes.reneFood.domain.model.Pedido;
import com.reneantunes.reneFood.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private EnvioEmailService envioEmailService;
	
	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		pedido.confirmar();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome()  + " - Pedido confirmado")
				.corpo("O pedido de código " + pedido.getCodigo() + " foi confirmado!")
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioEmailService.enviar(mensagem);
		
	}
	
	@Transactional
	public void entregar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		
		pedido.entregar();
	
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		pedido.cancelar();
	}
}
