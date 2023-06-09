package com.reneantunes.reneFood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reneantunes.reneFood.api.model.input.FormaPagamentoInput;
import com.reneantunes.reneFood.domain.model.FormaPagamento;

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
