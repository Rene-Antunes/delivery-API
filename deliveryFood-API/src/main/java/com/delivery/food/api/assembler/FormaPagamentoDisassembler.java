package com.delivery.food.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delivery.food.api.model.input.FormaPagamentoInput;
import com.delivery.food.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
		
		return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
		
	}
	
	public void copyToDomainObject(FormaPagamentoInput formaPgamentoInput, FormaPagamento formaPagamento) {
		
		modelMapper.map(formaPgamentoInput, formaPagamento);
		
	}
	
	
	

}
