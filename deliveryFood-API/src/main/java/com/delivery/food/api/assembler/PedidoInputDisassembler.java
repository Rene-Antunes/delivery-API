package com.delivery.food.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delivery.food.api.model.input.PedidoInput;
import com.delivery.food.domain.model.Pedido;

@Component
public class PedidoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Pedido toDomainObject(PedidoInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}
	
}
