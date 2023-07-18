package com.reneantunes.reneFood.domain.event;

import com.reneantunes.reneFood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {
	
	private Pedido pedido;
	
	
}
