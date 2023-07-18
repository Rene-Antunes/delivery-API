package com.reneantunes.reneFood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.reneantunes.reneFood.domain.event.PedidoConfirmadoEvent;
import com.reneantunes.reneFood.domain.model.Pedido;
import com.reneantunes.reneFood.domain.service.EnvioEmailService;
import com.reneantunes.reneFood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
	
	@Autowired
	private EnvioEmailService envioEmailService;
	
	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		
		Pedido pedido = event.getPedido();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome()  + " - Pedido confirmado")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioEmailService.enviar(mensagem);
	}
}
